package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.TopicName;

public interface SaveTopicName {

    TopicName save(TopicName topicName);
}
