package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.ArticlePriceBoundarySpecification;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.NewArticleBoundaryCommand;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.in.NewArticleNotificationUseCase;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.messaging.NewArticleBoundarySpecificationPublisherPort;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.SaveArticlePriceBoundarySpecification;

public class NewArticleNotificationService implements NewArticleNotificationUseCase {

    private final SaveArticlePriceBoundarySpecification saveArticlePriceBoundarySpecification;
    private final Mapper<ArticlePriceBoundarySpecification, SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto> mapper;
    private final NewArticleBoundarySpecificationPublisherPort publisher;

    public NewArticleNotificationService(
            SaveArticlePriceBoundarySpecification saveArticlePriceBoundarySpecification,
            Mapper<ArticlePriceBoundarySpecification, SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto> mapper,
            NewArticleBoundarySpecificationPublisherPort publisher) {
        this.saveArticlePriceBoundarySpecification = saveArticlePriceBoundarySpecification;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Identity accept(NewArticleBoundaryCommand command) {
        ArticlePriceBoundarySpecification articlePriceBoundary = ArticlePriceBoundarySpecification.from(command);
        Identity identity = new Identity(saveArticlePriceBoundarySpecification.save(mapper.to(articlePriceBoundary)));
        publisher.publish(new NewArticleBoundarySpecificationPublisherPort.NewArticleBoundarySpecificationEvent(
                articlePriceBoundary.getArticleId(), command.getBoundaryType(), articlePriceBoundary.getBoundary()));
        return identity;
    }
}
