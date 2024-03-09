package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;

public class ArticleNameHandlerImpl implements ArticleNameHandler {

    private final GetTopicName getTopicName;
    private final SaveTopicName saveTopicName;
    private final NewArticlePublisherPort newArticlePublisherPort;

    public ArticleNameHandlerImpl(
            GetTopicName getTopicName,
            SaveTopicName saveTopicName,
            NewArticlePublisherPort newArticlePublisherPort) {
        this.getTopicName = getTopicName;
        this.saveTopicName = saveTopicName;
        this.newArticlePublisherPort = newArticlePublisherPort;
    }

    @Override
    public void handle(ArticlePrice articlePrice) {
        GetTopicName.ArticlePriceTopicName byName = getTopicName.getByName(articlePrice.getName());
        if (byName == null) {
            NewArticlePublisherPort.TopicName topicName =
                    new NewArticlePublisherPort.TopicName(articlePrice.getName());
            newArticlePublisherPort.publish(
                    new NewArticlePublisherPort.TopicNameMessage(
                            topicName, new NewArticlePublisherPort.NewArticleMessage(topicName.name())));
            saveTopicName.save(
                    new GetTopicName.ArticlePriceTopicName(topicName.name(), articlePrice.getName()));
        }
    }
}
