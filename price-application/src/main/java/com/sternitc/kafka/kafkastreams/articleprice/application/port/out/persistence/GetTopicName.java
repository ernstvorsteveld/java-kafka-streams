package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface GetTopicName {

    ArticlePriceTopicName getByName(String articleName);

    record ArticlePriceTopicName(String topicName, String articleName) {
    }

}
