package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence;

public interface GetArticlePriceThreshold extends ArticlePriceThresholdDao {

    ArticlePriceThresholdDto get(String id);

}
