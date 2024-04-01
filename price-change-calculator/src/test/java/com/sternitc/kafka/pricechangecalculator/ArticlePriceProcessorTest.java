package com.sternitc.kafka.pricechangecalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.kafka.pricechangecalculator.application.domain.ArticlePrice;
import com.sternitc.kafka.pricechangecalculator.application.domain.ArticlePriceChange;
import com.sternitc.kafka.pricechangecalculator.application.domain.PriceChangeType;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@EmbeddedKafka(
        topics = {ArticlePriceProcessorTest.INPUT_TOPIC, ArticlePriceProcessorTest.OUTPUT_TOPIC},
        partitions = 1)
@ActiveProfiles("test")
class ArticlePriceProcessorTest {

    public static final String INPUT_TOPIC = "article-prices";
    public static final String OUTPUT_TOPIC = "article-price.changes";
    public static final String GROUP_NAME = "group1";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_send_price_and_receive_price_change(
            @Autowired EmbeddedKafkaBroker embeddedKafka) throws JsonProcessingException {
        KafkaTemplate<String, String> template = expectTemplate(embeddedKafka);

        template.sendDefault("10", objectMapper.writeValueAsString(new ArticlePrice("10", 100)));
        template.sendDefault("10", objectMapper.writeValueAsString(new ArticlePrice("10", 1000)));
        template.sendDefault("10", objectMapper.writeValueAsString(new ArticlePrice("10", 50)));

        ConsumerRecords<String, String> records = expectConsumerRecords(embeddedKafka);

        assertThat(records.count()).isEqualTo(3);
        Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
        assertThat(iterator.next().value()).isEqualTo(
                objectMapper.writeValueAsString(
                        new ArticlePriceChange(
                                "10",
                                new ArticlePrice("10", 100),
                                new ArticlePrice("10", 100),
                                PriceChangeType.SAME)));
        assertThat(iterator.next().value()).isEqualTo(
                objectMapper.writeValueAsString(
                        new ArticlePriceChange(
                                "10",
                                new ArticlePrice("10", 100),
                                new ArticlePrice("10", 1000),
                                PriceChangeType.INCREASED)));
        assertThat(iterator.next().value()).isEqualTo(
                objectMapper.writeValueAsString(
                        new ArticlePriceChange(
                                "10",
                                new ArticlePrice("10", 1000),
                                new ArticlePrice("10", 50),
                                PriceChangeType.DECREASED)));
    }

    private ConsumerRecords<String, String> expectConsumerRecords(EmbeddedKafkaBroker embeddedKafka) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(GROUP_NAME, "false", embeddedKafka);
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", StringDeserializer.class);
        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);

        Consumer<String, String> consumer = cf.createConsumer();
        consumer.assign(Collections.singleton(new TopicPartition(OUTPUT_TOPIC, 0)));
        ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(10));
        consumer.commitSync();
        return poll;
    }

    private KafkaTemplate<String, String> expectTemplate(EmbeddedKafkaBroker embeddedKafka) {
        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
        senderProps.put("key.serializer", StringSerializer.class);
        senderProps.put("value.serializer", StringSerializer.class);
        DefaultKafkaProducerFactory<String, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
        KafkaTemplate<String, String> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic(INPUT_TOPIC);
        return template;
    }

}