package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceThresholdMessagingConfiguration {

    @Value("${topic.new.article.prices.boundary.name}")
    public String newPriceThresholdTopic;

    @Bean
    public NewPriceThresholdPublisherPort newPriceThresholdPublisherPort(
            StreamBridge streamBridge) {
        return new NewPriceThresholdPublisherPortKafka(streamBridge, newPriceThresholdTopic);
    }

}
