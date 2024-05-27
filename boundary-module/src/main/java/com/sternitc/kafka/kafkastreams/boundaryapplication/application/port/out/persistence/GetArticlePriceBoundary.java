package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

public interface GetArticlePriceThreshold extends ArticlePriceThresholdDao {

    ArticlePriceThresholdDto get(String id);

}
