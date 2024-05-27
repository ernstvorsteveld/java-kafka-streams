package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.persistence.ArticlePriceBoundaryDaoInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class PriceThresholdApplicationPersistenceInMemoryConfiguration {

    @Bean
    public ArticlePriceBoundaryDaoInMemory articlePriceThresholdDaoInMemory() {
        return new ArticlePriceBoundaryDaoInMemory();
    }

    @Bean
    GetArticlePriceBoundary getArticlePriceThreshold() {
        return articlePriceThresholdDaoInMemory();
    }

    @Bean
    public SaveArticlePriceBoundary saveArticlePriceThreshold() {
        return articlePriceThresholdDaoInMemory();
    }

}
