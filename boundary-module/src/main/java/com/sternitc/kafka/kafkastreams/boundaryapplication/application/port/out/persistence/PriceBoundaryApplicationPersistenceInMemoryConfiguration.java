package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.persistence.ArticlePriceBoundaryDaoInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class PriceBoundaryApplicationPersistenceInMemoryConfiguration {

    @Bean
    public ArticlePriceBoundaryDaoInMemory articlePriceBoundaryDaoInMemory() {
        return new ArticlePriceBoundaryDaoInMemory();
    }

    @Bean
    GetArticlePriceBoundary getArticlePriceBoundary() {
        return articlePriceBoundaryDaoInMemory();
    }

    @Bean
    public SaveArticlePriceBoundary saveArticlePriceBoundary() {
        return articlePriceBoundaryDaoInMemory();
    }

}
