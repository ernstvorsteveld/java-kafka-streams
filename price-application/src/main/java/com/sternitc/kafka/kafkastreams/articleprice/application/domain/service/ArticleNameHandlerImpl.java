package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.TopicDao;

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
        TopicDao.ArticlePriceTopicNameDto byName = getTopicName.getByName(articlePrice.getName());
        if (byName == null) {
            NewArticlePublisherPort.TopicName topicName =
                    new NewArticlePublisherPort.TopicName(articlePrice.getName());
            newArticlePublisherPort.publish(
                    new NewArticlePublisherPort.TopicNameEvent(
                            topicName, new NewArticlePublisherPort.NewArticleMessage(topicName.name())));
            saveTopicName.save(
                    new TopicDao.ArticlePriceTopicNameDto(topicName.name(), articlePrice.getName()));
        }
    }
}
