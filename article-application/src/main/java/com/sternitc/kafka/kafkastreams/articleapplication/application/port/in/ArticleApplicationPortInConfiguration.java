package com.sternitc.kafka.kafkastreams.articleapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service.Mapper;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service.NewArticleNotificationService;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.messaging.NewArticleBoundarySpecificationPublisherPort;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.SaveArticlePriceBoundarySpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleApplicationPortInConfiguration {

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

}
