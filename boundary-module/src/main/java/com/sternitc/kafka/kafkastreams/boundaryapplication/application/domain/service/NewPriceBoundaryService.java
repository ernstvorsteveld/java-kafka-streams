package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceBoundary;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceBoundaryCommand;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceBoundaryUseCase;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.ArticlePriceBoundaryDao;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.SaveArticlePriceBoundary;

public class NewPriceBoundaryService implements NewPriceBoundaryUseCase {

    private final SaveArticlePriceBoundary saveArticlePriceBoundarySpecification;
    private final Mapper<ArticlePriceBoundary, ArticlePriceBoundaryDao.ArticlePriceBoundaryDto> mapper;
    private final NewPriceThresholdPublisherPort publisher;

    public NewPriceBoundaryService(
            SaveArticlePriceBoundary saveArticlePriceBoundarySpecification,
            Mapper<ArticlePriceBoundary, ArticlePriceBoundaryDao.ArticlePriceBoundaryDto> mapper,
            NewPriceThresholdPublisherPort publisher) {
        this.saveArticlePriceBoundarySpecification = saveArticlePriceBoundarySpecification;
        this.mapper = mapper;
        this.publisher = publisher;
    }

    @Override
    public Identity accept(NewPriceBoundaryCommand command) {
        ArticlePriceBoundary articlePriceBoundary = ArticlePriceBoundary.from(command);
        Identity identity = new Identity(saveArticlePriceBoundarySpecification.save(mapper.to(articlePriceBoundary)));
        publisher.publish(new NewPriceThresholdPublisherPort.NewArticlePriceBoundaryEvent(articlePriceBoundary));
        return identity;
    }

}
