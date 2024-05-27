package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import org.springframework.context.annotation.Bean;

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
