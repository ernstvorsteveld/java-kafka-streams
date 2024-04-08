package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterHttpConfiguration {

    @Value("${com.sternitc.pricethresholdapplication.adapter.in.http.location}")
    public String location;

    @Bean
    public LocationCreator locationCreator() {
        return new LocationCreator(location);
    }

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

    @Bean
    public PriceThresholdsController priceThresholdsController(
            NewPriceThresholdUseCase newPriceThresholdUseCase,
            LocationCreator locationCreator,
            Mapper mapper) {
        return new PriceThresholdsController(newPriceThresholdUseCase, locationCreator, mapper);
    }
}
