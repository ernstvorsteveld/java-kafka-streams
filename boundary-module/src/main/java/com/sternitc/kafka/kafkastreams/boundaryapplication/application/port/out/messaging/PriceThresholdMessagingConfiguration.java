package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging.NewPriceBoundaryPublisherPortKafka;
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
        return new NewPriceBoundaryPublisherPortKafka(streamBridge, newPriceThresholdTopic);
    }

}
