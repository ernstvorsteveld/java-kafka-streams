package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceThreshold;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceThresholdCommand;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.ArticlePriceThresholdDao;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.SaveArticlePriceThreshold;

public class NewPriceThresholdService implements NewPriceThresholdUseCase {

    private final SaveArticlePriceThreshold saveArticlePriceThresholdSpecification;
    private final Mapper<ArticlePriceThreshold, ArticlePriceThresholdDao.ArticlePriceThresholdDto> mapper;
    private final NewPriceThresholdPublisherPort publisher;

    public NewPriceThresholdService(
            SaveArticlePriceThreshold saveArticlePriceThresholdSpecification,
            Mapper<ArticlePriceThreshold, ArticlePriceThresholdDao.ArticlePriceThresholdDto> mapper,
            NewPriceThresholdPublisherPort publisher) {
        this.saveArticlePriceThresholdSpecification = saveArticlePriceThresholdSpecification;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Identity accept(NewPriceThresholdCommand command) {
        ArticlePriceThreshold articlePriceThreshold = ArticlePriceThreshold.from(command);
        Identity identity = new Identity(saveArticlePriceThresholdSpecification.save(mapper.to(articlePriceThreshold)));
        publisher.publish(new NewPriceThresholdPublisherPort.NewArticlePriceThresholdEvent(articlePriceThreshold));
        return identity;
    }

}
