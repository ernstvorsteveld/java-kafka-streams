package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface TopicDao {

    record ArticlePriceTopicNameDto(String topicName, String articleName) {
    }

}
