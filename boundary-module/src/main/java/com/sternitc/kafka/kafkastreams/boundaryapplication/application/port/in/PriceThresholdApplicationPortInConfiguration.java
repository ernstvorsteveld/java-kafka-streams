package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceBoundary;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service.Mapper;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service.NewPriceBoundaryService;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.ArticlePriceBoundaryDao;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.SaveArticlePriceBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceThresholdApplicationPortInConfiguration {

    @Bean
    public NewPriceBoundaryUseCase newPriceThresholdUseCase(
            SaveArticlePriceBoundary saveArticlePriceBoundary,
            Mapper<ArticlePriceBoundary, ArticlePriceBoundaryDao.ArticlePriceBoundaryDto> mapper,
            NewPriceThresholdPublisherPort publisher) {
        return new NewPriceBoundaryService(
                saveArticlePriceBoundary,
                mapper,
                publisher);
    }

}
