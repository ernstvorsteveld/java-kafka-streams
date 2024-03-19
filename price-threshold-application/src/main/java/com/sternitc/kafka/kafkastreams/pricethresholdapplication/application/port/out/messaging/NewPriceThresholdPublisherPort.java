package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging;

public interface NewPriceThresholdPublisherPort {

    void publish(NewArticlePriceThresholdEvent event);

    record NewArticlePriceThresholdEvent(String articleId, String boundaryType, int value) {
    }

}
