package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface GetTopicName extends TopicDao {

    ArticlePriceTopicNameDto getByName(String articleName);

}
