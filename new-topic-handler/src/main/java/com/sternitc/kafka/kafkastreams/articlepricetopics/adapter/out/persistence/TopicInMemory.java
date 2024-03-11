package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.GetTopic;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.SaveTopic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TopicInMemory implements GetTopic, SaveTopic {

    private final Map<String, Topic> topics = new HashMap<>();

    @Override
    public Topic get(TopicName name) {
        return topics.getOrDefault(name.name(), null);
    }

    @Override
    public Collection<Topic> get(Page page) {
        return topics.entrySet()
                .stream()
                .skip(page.start())
                .limit(page.pageSize())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public Topic save(Topic topic) {
        Topic toStore = new Topic(topic.name(), topic.receivedAt(), topic.handledAt(), UUID.randomUUID().toString());
        topics.put(topic.name().name(), toStore);
        return toStore;
    }
}
