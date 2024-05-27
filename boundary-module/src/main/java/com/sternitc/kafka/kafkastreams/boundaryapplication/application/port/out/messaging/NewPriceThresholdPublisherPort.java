package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceBoundary;

public interface NewPriceThresholdPublisherPort {

    void publish(NewArticlePriceBoundaryEvent event);

    record NewArticlePriceBoundaryEvent(String articleId,
                                        String companyId,
                                        String userId,
                                        String boundaryType,
                                        int value) {
        public NewArticlePriceBoundaryEvent(ArticlePriceBoundary articlePriceBoundary) {
            this(articlePriceBoundary.getArticleId(),
                    articlePriceBoundary.getCompanyId(),
                    articlePriceBoundary.getUserId(),
                    articlePriceBoundary.getBoundaryType().name(),
                    articlePriceBoundary.getBoundary());
        }
    }

}
