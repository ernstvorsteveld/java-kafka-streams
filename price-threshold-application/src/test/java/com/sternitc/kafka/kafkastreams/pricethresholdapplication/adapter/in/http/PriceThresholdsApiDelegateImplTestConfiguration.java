package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class PriceThresholdsApiDelegateImplTestConfiguration {

    @Bean
    public LocationCreator locationCreator() {
        return new LocationCreator("location");
    }

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

}
