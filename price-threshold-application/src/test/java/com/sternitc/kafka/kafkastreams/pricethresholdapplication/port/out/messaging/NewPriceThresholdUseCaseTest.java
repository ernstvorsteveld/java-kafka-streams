package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sternitc.kafka.kafkastreams.PriceThresholdApplication;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort.NewArticlePriceThresholdEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(
        classes = {PriceThresholdApplication.class}
)
@EmbeddedKafka(
        partitions = 1,
        topics = {"${topic.new.article.prices.boundary.name}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}
)
public class NewPriceThresholdUseCaseTest {

    @Value("${topic.new.article.prices.boundary.name}")
    public String newPriceBoundaryTopic;

    @Autowired
    private NewPriceThresholdPublisherPort newPriceThresholdPublisherPort;

    @Test
    public void should_send(@Autowired EmbeddedKafkaBroker embeddedKafkaBroker) throws JsonProcessingException, InterruptedException {
        newPriceThresholdPublisherPort.publish(
                new NewArticlePriceThresholdEvent("10", "11", "12", "UPPER", 10));

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group1", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, NewArticlePriceThresholdEvent> cf = new DefaultKafkaConsumerFactory<>(
                consumerProps, new StringDeserializer(),
                new JsonDeserializer<>(NewArticlePriceThresholdEvent.class, false));
        Consumer<String, NewArticlePriceThresholdEvent> consumer = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, newPriceBoundaryTopic);
        ConsumerRecords<String, NewArticlePriceThresholdEvent> records =
                KafkaTestUtils.getRecords(consumer, Duration.of(3, ChronoUnit.SECONDS), 1);

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
