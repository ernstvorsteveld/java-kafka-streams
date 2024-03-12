package com.sternitc.kafka.kafkastreams.articleapplication;

import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.messaging.NewArticleBoundarySpecificationPublisherPort;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service.ArticlePriceBoundarySpecificationMapper;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service.Mapper;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service.NewArticleNotificationService;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.in.NewArticleNotificationUseCase;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.SaveArticlePriceBoundarySpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleApplicationConfiguration {

    @Bean
    public NewArticleNotificationUseCase newArticleNotificationUserCase(
            SaveArticlePriceBoundarySpecification saveArticlePriceBoundarySpecification,
            Mapper<com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.ArticlePriceBoundarySpecification, SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto> mapper,
            NewArticleBoundarySpecificationPublisherPort publisher) {
        return new NewArticleNotificationService(
                saveArticlePriceBoundarySpecification,
                mapper,
                publisher);
    }

    @Bean
    public ArticlePriceBoundarySpecificationMapper articlePriceBoundarySpecificationMapper() {
        return new ArticlePriceBoundarySpecificationMapper();
    }

}
