package com.sternitc.kafka.kafkastreams.articleapplication.port.out.persistence;

import com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.persistence.ArticlePriceBoundarySpecificationDaoInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class ArticleApplicationPersistenceInMemoryConfiguration {

    @Bean
    public ArticlePriceBoundarySpecificationDaoInMemory articlePriceBoundarySpecificationDaoInMemory() {
        return new ArticlePriceBoundarySpecificationDaoInMemory();
    }

    @Bean GetArticlePriceBoundarySpecification getArticlePriceBoundarySpecification() {
        return articlePriceBoundarySpecificationDaoInMemory();
    }

    @Bean
    public SaveArticlePriceBoundarySpecification saveArticlePriceBoundarySpecification() {
        return articlePriceBoundarySpecificationDaoInMemory();
    }

}
