package com.sternitc.kafka.kafkastreams.articleapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.messaging.NewArticleBoundarySpecificationPublisherPortKafka;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Profile("kafka")
public class ArticleBoundaryMessagingConfiguration {

    @Bean
    public NewArticleBoundarySpecificationPublisherPort newArticleBoundarySpecificationPublisherPort(
            KafkaTemplate<String, NewArticleBoundarySpecificationPublisherPortKafka.NewArticlePriceBoundarySpecificationMessage> articlePriceBoundaryKafkaTemplate) {
        return new NewArticleBoundarySpecificationPublisherPortKafka(articlePriceBoundaryKafkaTemplate);
    }
}
