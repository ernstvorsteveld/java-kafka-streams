package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterHttpConfiguration {

    @Value("${com.sternitc.priceboundariesapplication.adapter.in.http.location}")
    public String location;

    @Bean
    public LocationCreator locationCreator() {
        return new LocationCreator(location);
    }

    @Bean
    public Mapper mapper() {
        return new Mapper();
    }

//    @Bean
//    public PriceThresholdsApiDelegateImpl priceThresholdsApiDelegate(
//            NewPriceThresholdUseCase newPriceThresholdUseCase,
//            LocationCreator locationCreator,
//            Mapper mapper) {
//        return new PriceThresholdsApiDelegateImpl(newPriceThresholdUseCase, locationCreator, mapper);
//    }

//    @Autowired
//    public PriceThresholdsApi api;
}
