package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

public interface NewPriceThresholdPublisherPort {

    void publish(NewArticlePriceThresholdEvent event);

    record NewArticlePriceThresholdEvent(String articleId, String companyId, String userId, String boundaryType, int value) {
    }

}
