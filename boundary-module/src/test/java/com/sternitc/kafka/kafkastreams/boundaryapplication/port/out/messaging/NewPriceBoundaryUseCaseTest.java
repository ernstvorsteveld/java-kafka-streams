package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sternitc.kafka.kafkastreams.BoundaryApplication;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort.NewArticlePriceBoundaryEvent;
import org.apache.kafka.clients.consumer.Consumer;
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
        classes = {BoundaryApplication.class}
)
@EmbeddedKafka(
        partitions = 1,
        topics = {"${topic.new.article.prices.boundary.name}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}
)
public class NewPriceBoundaryUseCaseTest {

    @Value("${topic.new.article.prices.boundary.name}")
    public String newPriceBoundaryTopic;

    @Autowired
    private NewPriceThresholdPublisherPort newPriceThresholdPublisherPort;

    @Test
    public void should_send(@Autowired EmbeddedKafkaBroker embeddedKafkaBroker) throws JsonProcessingException, InterruptedException {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group1", "false", embeddedKafkaBroker);
        ConsumerFactory<String, NewArticlePriceBoundaryEvent> cf = new DefaultKafkaConsumerFactory<>(
                consumerProps, new StringDeserializer(),
                new JsonDeserializer<>(NewArticlePriceBoundaryEvent.class, false));
        Consumer<String, NewArticlePriceBoundaryEvent> consumer = cf.createConsumer();
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, newPriceBoundaryTopic);

        newPriceThresholdPublisherPort.publish(
                new NewArticlePriceBoundaryEvent("10", "11", "12", "INCREASE", 10));

        ConsumerRecords<String, NewArticlePriceBoundaryEvent> records =
                KafkaTestUtils.getRecords(consumer, Duration.of(3, ChronoUnit.SECONDS), 1);

        assertThat(records.count()).isEqualTo(1);
    }

}
