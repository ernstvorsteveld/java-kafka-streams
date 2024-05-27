package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.ArticlePriceThreshold;

public interface NewPriceThresholdPublisherPort {

    void publish(NewArticlePriceThresholdEvent event);

    record NewArticlePriceThresholdEvent(String articleId,
                                         String companyId,
                                         String userId,
                                         String boundaryType,
                                         int value) {
        public NewArticlePriceThresholdEvent(ArticlePriceThreshold articlePriceThreshold) {
            this(articlePriceThreshold.getArticleId(),
                    articlePriceThreshold.getCompanyId(),
                    articlePriceThreshold.getUserId(),
                    articlePriceThreshold.getBoundaryType().name(),
                    articlePriceThreshold.getBoundary());
        }
    }

}
