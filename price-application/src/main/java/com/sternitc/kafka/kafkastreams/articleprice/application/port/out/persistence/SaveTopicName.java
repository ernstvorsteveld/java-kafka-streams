package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface SaveTopicName extends TopicDao {

    void save(ArticlePriceTopicNameDto articlePriceTopicNameDto);
}
