package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface SaveTopicName {

    void save(GetTopicName.ArticlePriceTopicName articlePriceTopicName);
}
