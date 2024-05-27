package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleApplicationServiceConfiguration {

    @Bean
    public ArticlePriceThresholdMapper articlePriceBoundarySpecificationMapper() {
        return new ArticlePriceThresholdMapper();
    }

}
