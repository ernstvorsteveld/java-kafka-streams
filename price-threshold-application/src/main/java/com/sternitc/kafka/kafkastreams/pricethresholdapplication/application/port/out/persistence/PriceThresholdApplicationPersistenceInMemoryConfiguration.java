package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.persistence.ArticlePriceThresholdDaoInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class PriceThresholdApplicationPersistenceInMemoryConfiguration {

    @Bean
    public ArticlePriceThresholdDaoInMemory articlePriceThresholdDaoInMemory() {
        return new ArticlePriceThresholdDaoInMemory();
    }

    @Bean
    GetArticlePriceThreshold getArticlePriceThreshold() {
        return articlePriceThresholdDaoInMemory();
    }

    @Bean
    public SaveArticlePriceThreshold saveArticlePriceThreshold() {
        return articlePriceThresholdDaoInMemory();
    }

}
