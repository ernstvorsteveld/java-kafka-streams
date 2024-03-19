package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Profile("kafka")
public class PriceThresholdMessagingConfiguration {

    @Bean
    public NewPriceThresholdPublisherPort newPriceThresholdPublisherPort(
            KafkaTemplate<String, NewPriceThresholdPublisherPortKafka.NewArticlePriceThresholdMessage> articlePriceThresholdKafkaTemplate) {
        return new NewPriceThresholdPublisherPortKafka(articlePriceThresholdKafkaTemplate);
    }
}
