package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence;

public interface ArticlePriceThresholdDao {

    record ArticlePriceThresholdDto(String articleId, String thresholdType, int value) {
    }
}
