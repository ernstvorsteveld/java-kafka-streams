package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;

import static com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort.*;
import static com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.TopicDao.ArticlePriceTopicNameDto;

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
        ArticlePriceTopicNameDto byName = getTopicName.getByName(articlePrice.getName());
        if (byName == null) {
            TopicName topicName = new TopicName(articlePrice.getName());
            newArticlePublisherPort.publish(new TopicNameEvent(topicName, new NewArticleMessage(topicName.name())));
            saveTopicName.save(new ArticlePriceTopicNameDto(topicName.name(), articlePrice.getName()));
        }
    }
}
