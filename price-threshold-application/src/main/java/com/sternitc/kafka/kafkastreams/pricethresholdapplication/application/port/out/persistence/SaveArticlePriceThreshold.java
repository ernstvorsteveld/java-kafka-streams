package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence;

public interface SaveArticlePriceThreshold extends ArticlePriceThresholdDao {

    String save(ArticlePriceThresholdDto dto);

}
