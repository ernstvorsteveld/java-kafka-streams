package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.ArticlePriceThreshold;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.NewPriceThresholdCommand;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort.NewArticlePriceThresholdEvent;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.ArticlePriceThresholdDao.ArticlePriceThresholdDto;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.SaveArticlePriceThreshold;

public class NewPriceThresholdService implements NewPriceThresholdUseCase {

    private final SaveArticlePriceThreshold saveArticlePriceThresholdSpecification;
    private final Mapper<ArticlePriceThreshold, ArticlePriceThresholdDto> mapper;
    private final NewPriceThresholdPublisherPort publisher;

    public NewPriceThresholdService(
            SaveArticlePriceThreshold saveArticlePriceThresholdSpecification,
            Mapper<ArticlePriceThreshold, ArticlePriceThresholdDto> mapper,
            NewPriceThresholdPublisherPort publisher) {
        this.saveArticlePriceThresholdSpecification = saveArticlePriceThresholdSpecification;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Identity accept(NewPriceThresholdCommand command) {
        ArticlePriceThreshold articlePriceThreshold = ArticlePriceThreshold.from(command);
        Identity identity = new Identity(saveArticlePriceThresholdSpecification.save(mapper.to(articlePriceThreshold)));
        publisher.publish(new NewArticlePriceThresholdEvent(articlePriceThreshold));
        return identity;
    }

}
