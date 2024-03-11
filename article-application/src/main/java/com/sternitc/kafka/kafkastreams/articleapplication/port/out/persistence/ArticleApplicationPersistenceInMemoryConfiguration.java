package com.sternitc.kafka.kafkastreams.articleapplication.port.out.persistence;

import com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.persistence.SaveArticlePriceBoundarySpecificationDaoInMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class ArticleApplicationPersistenceInMemoryConfiguration {

    @Bean
    public SaveArticlePriceBoundarySpecification saveArticleNotification() {
        return articleNotificationDaoInMemory();
    }

    @Bean GetArticlePriceBoundarySpecification getArticlePriceBoundarySpecification() {
        return articleNotificationDaoInMemory();
    }

    @Bean
    public SaveArticlePriceBoundarySpecificationDaoInMemory articleNotificationDaoInMemory() {
        return new SaveArticlePriceBoundarySpecificationDaoInMemory();
    }

}
