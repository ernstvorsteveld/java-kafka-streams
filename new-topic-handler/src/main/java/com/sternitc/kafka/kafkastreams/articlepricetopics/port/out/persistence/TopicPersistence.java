package com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence;

public interface TopicPersistence {

    record TopicName(String name) {
    }

    record Topic(TopicName name, long receivedAt, long handledAt, String id) {
    }

}
