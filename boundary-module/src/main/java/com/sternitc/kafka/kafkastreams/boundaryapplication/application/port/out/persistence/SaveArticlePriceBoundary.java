package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

public interface SaveArticlePriceThreshold extends ArticlePriceThresholdDao {

    String save(ArticlePriceThresholdDto dto);

}
