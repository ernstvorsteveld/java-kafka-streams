package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka.NewArticlePriceThresholdMessage;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort.NewArticlePriceThresholdEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.function.Function;

@Configuration
public class PriceThresholdMessagingConfiguration {

    @Bean
    public NewPriceThresholdPublisherPort newPriceThresholdPublisherPort(
            StreamBridge streamBridge) {
        String priceBoundaryTopic = "new-price-threshold";
        return new NewPriceThresholdPublisherPortKafka(streamBridge, priceBoundaryTopic);
    }

}
