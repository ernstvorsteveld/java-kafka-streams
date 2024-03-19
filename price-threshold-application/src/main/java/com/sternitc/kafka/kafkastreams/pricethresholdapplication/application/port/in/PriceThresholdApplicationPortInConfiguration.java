package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.ArticlePriceThreshold;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.service.Mapper;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.service.NewPriceThresholdService;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.ArticlePriceThresholdDao;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.SaveArticlePriceThreshold;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceThresholdApplicationPortInConfiguration {

    @Bean
    public NewPriceThresholdUseCase newPriceThresholdUseCase(
            SaveArticlePriceThreshold saveArticlePriceThreshold,
            Mapper<ArticlePriceThreshold, ArticlePriceThresholdDao.ArticlePriceThresholdDto> mapper,
            NewPriceThresholdPublisherPort publisher) {
        return new NewPriceThresholdService(
                saveArticlePriceThreshold,
                mapper,
                publisher);
    }

}
