package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

public interface DeleteTopicName extends TopicDao {

    void delete(String name);
}
