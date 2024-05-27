package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleApplicationServiceConfiguration {

    @Bean
    public ArticlePriceBoundaryMapper articlePriceBoundarySpecificationMapper() {
        return new ArticlePriceBoundaryMapper();
    }

}
