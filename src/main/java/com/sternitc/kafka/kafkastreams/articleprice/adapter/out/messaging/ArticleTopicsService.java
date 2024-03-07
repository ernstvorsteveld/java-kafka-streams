package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.SaveTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;

import java.util.HashMap;
import java.util.Map;

public class ArticleTopicsService {


    private final SaveTopicName saveTopicName;
    private final GetTopicName getTopicName;

    public ArticleTopicsService(
            SaveTopicName saveTopicName,
            GetTopicName getTopicName) {
        this.saveTopicName = saveTopicName;
        this.getTopicName = getTopicName;
    }

    public TopicName get(String articleName) throws IsNewArticlePriceTopic {
        TopicName topicName = getTopicName.get(articleName);
        if (topicName != null) {
            return topicName;
        } else {
            throw new IsNewArticlePriceTopic(articleName);
        }
    }

    public TopicName get(ArticlePrice price) throws IsNewArticlePriceTopic {
        return get(price.getName());
    }

}
