package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleApplicationServiceConfiguration {

    @Bean
    public ArticlePriceBoundarySpecificationMapper articlePriceBoundarySpecificationMapper() {
        return new ArticlePriceBoundarySpecificationMapper();
    }

}
