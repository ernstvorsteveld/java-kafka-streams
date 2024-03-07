package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.inmemory;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.TopicName;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.SaveTopicName;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryTopicNameStoreImpl implements SaveTopicName, GetTopicName {

    private final Map<String, TopicName> topics = new HashMap<>();

    @Override
    public TopicName save(TopicName topicName) {
        topicName.setId(UUID.randomUUID().toString());
        topics.put(topicName.getId(), topicName);
        return topicName;
    }

    @Override
    public TopicName get(String articleName) {
        return topics.get(TopicName.newName(articleName));
    }
}
