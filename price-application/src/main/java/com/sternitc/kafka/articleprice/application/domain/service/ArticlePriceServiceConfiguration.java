package com.sternitc.kafka.articleprice.application.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePriceServiceConfiguration {

    @Bean
    public ArticlePriceMapper articlePriceMapper() {
        return new ArticlePriceMapper();
    }

}
