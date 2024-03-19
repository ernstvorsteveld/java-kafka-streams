package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.kafka.kafkastreams.PriceThresholdApplication;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewArticlePriceThresholdMessageDeserializer;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewArticlePriceThresholdMessageSerializer;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka.NewArticlePriceThresholdMessage;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@SpringBootApplication(
        scanBasePackageClasses = PriceThresholdApplication.class,
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class
        }
)
@EmbeddedKafka(
        topics = {NewPriceThresholdUseCaseTest.INPUT_TOPIC, NewPriceThresholdUseCaseTest.OUTPUT_TOPIC},
        partitions = 1)
@ActiveProfiles(profiles = {"test"})
public class NewPriceThresholdUseCaseTest {
    public static final String INPUT_TOPIC = "new-price-threshold";
    public static final String OUTPUT_TOPIC = "new-price-threshold";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private NewPriceThresholdPublisherPort newPriceThresholdPublisherPort;

    @Autowired
    private Map<String, NewArticlePriceThresholdMessage> msg;

    @Test
    public void should_send(@Autowired EmbeddedKafkaBroker embeddedKafka) throws JsonProcessingException, InterruptedException {
//        newPriceThresholdPublisherPort.publish(
//                new NewArticlePriceThresholdEvent("10", "UPPER", 10));

        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
        senderProps.put("key.serializer", StringSerializer.class);
        senderProps.put("value.serializer", NewArticlePriceThresholdMessageSerializer.class);
        DefaultKafkaProducerFactory<String, NewArticlePriceThresholdMessage> pf = new DefaultKafkaProducerFactory<>(senderProps);
        KafkaTemplate<String, NewArticlePriceThresholdMessage> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic(INPUT_TOPIC);
        template.sendDefault("10", new NewArticlePriceThresholdMessage("10", "UPPER", 10));

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group1", "false", embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", NewArticlePriceThresholdMessageDeserializer.class);
        DefaultKafkaConsumerFactory<byte[], byte[]> cf = new DefaultKafkaConsumerFactory<>(consumerProps);

        Consumer<byte[], byte[]> consumer = cf.createConsumer();
        consumer.assign(Collections.singleton(new TopicPartition(OUTPUT_TOPIC, 0)));
        ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofSeconds(10));
        consumer.commitSync();

        assertThat(records.count()).isEqualTo(1);

//        assertThat(msg.entrySet().size()).isEqualTo(1);
//        assertThat(msg.entrySet().iterator().next().getValue()).isEqualTo(
//                objectMapper.writeValueAsString(
//                        new NewArticlePriceThresholdMessage(
//                                "10",
//                                "UPPER",
//                                10)));

    }

}
