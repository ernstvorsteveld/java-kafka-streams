package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.inmemory;

import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.DeleteTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;

import java.util.HashMap;
import java.util.Map;

public class InMemoryTopicNameStoreAdapter implements SaveTopicName, GetTopicName, DeleteTopicName {

    private final Map<String, ArticlePriceTopicName> topics = new HashMap<>();

    @Override
    public ArticlePriceTopicName getByName(String articleName) {
        if (topics.containsKey(articleName)) {
            return topics.get(articleName);
        }
        return null;
    }

    @Override
    public void delete(String name) {
        topics.remove(name);
    }

    @Override
    public void save(ArticlePriceTopicName articlePriceTopicName) {
        topics.put(articlePriceTopicName.articleName(), articlePriceTopicName);
    }
}
