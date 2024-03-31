package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceThresholdMessagingConfiguration {

    public static final String PRICE_BOUNDARY_TOPIC = "new-price-threshold";

    @Bean
    public NewPriceThresholdPublisherPort newPriceThresholdPublisherPort(
            StreamBridge streamBridge) {
        return new NewPriceThresholdPublisherPortKafka(streamBridge, PRICE_BOUNDARY_TOPIC);
    }

}
