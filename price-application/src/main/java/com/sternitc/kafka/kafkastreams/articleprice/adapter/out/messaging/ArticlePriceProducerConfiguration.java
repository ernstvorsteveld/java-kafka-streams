package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ArticlePriceProducerConfiguration {

    @Value("${spring.kafka.articles.bootstrap-servers}")
    private String bootstrapServers;

    public static final String newArticleTopic = "new_article_topic";

    @Bean
    public <K, V> ProducerFactory<K, V> articlePriceProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public <K, V> KafkaTemplate<K, V> articlePriceKafkaTemplate() {
        return new KafkaTemplate<>(articlePriceProducerFactory());
    }

    @Bean
    public <K, V> ProducerFactory<K, V> newTopicProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public <K, V> KafkaTemplate<K, V> newTopicKafkaTemplate() {
        return new KafkaTemplate<>(articlePriceProducerFactory());
    }
}
