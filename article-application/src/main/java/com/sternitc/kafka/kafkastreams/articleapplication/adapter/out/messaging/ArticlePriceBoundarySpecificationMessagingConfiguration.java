package com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.messaging;

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
public class ArticlePriceBoundarySpecificationMessagingConfiguration {

    @Value("${spring.kafka.articles.bootstrap-servers}")
    private String bootstrapServers;

    public static final String priceBoundaryTopic = "price_boundary_topic";

    @Bean
    public <K, V> ProducerFactory<K, V> articlePriceBoundaryProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public <K, V> KafkaTemplate<K, V> articlePriceBoundaryKafkaTemplate() {
        return new KafkaTemplate<>(articlePriceBoundaryProducerFactory());
    }

    @Bean
    public <K, V> ProducerFactory<K, V> newArticlePriceBoundarySpecificationProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public <K, V> KafkaTemplate<K, V> newTopicKafkaTemplate() {
        return new KafkaTemplate<>(newArticlePriceBoundarySpecificationProducerFactory());
    }

    @Bean
    public NewArticleBoundarySpecificationPublisherPortKafka newArticleBoundarySpecificationPublisherPortKafka(
            KafkaTemplate<String, NewArticleBoundarySpecificationPublisherPortKafka.NewArticlePriceBoundarySpecificationMessage> articlePriceBoundaryKafkaTemplate) {
        return new NewArticleBoundarySpecificationPublisherPortKafka(articlePriceBoundaryKafkaTemplate);
    }
}
