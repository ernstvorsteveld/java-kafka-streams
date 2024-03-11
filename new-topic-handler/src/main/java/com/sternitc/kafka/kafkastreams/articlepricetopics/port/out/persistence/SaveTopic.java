package com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence;

public interface SaveTopic extends TopicPersistence {

    Topic save(Topic topic);

}
