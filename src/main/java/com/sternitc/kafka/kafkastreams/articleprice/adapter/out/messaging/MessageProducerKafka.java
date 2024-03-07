package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.ArticlePricePublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

public class MessageProducerKafka implements ArticlePricePublisherPort {

    private static final Logger logger = Logger.getLogger(MessageProducerKafka.class.getName());
    private final KafkaTemplate<String, ArticlePrice> articlePriceKafkaTemplate;
    private final KafkaTemplate<String, String> newTopicKafkaTemplate;
    private final ArticleTopicsService topics;

    public MessageProducerKafka(
            KafkaTemplate<String, ArticlePrice> articlePriceKafkaTemplate,
            KafkaTemplate<String, String> newTopicKafkaTemplate,
            ArticleTopicsService topics) {
        this.articlePriceKafkaTemplate = articlePriceKafkaTemplate;
        this.newTopicKafkaTemplate = newTopicKafkaTemplate;
        this.topics = topics;
    }

    @Override
    public void publish(ArticlePrice articlePrice) {
        try {
            TopicName topicName = topics.get(articlePrice);
            articlePriceKafkaTemplate.send(topicName.getTopicName(), articlePrice);
        } catch (IsNewArticlePriceTopic e) {
            handleMissingTopic(articlePrice);
        }
    }

    private void handleMissingTopic(ArticlePrice price) {
        logger.info(String.format("New article received, handle new topic creation, article name %s", price.getName()));
        TopicName topicName = new TopicName(price.getName());
        newTopicKafkaTemplate.send(ArticlePriceProducerConfig.newArticleTopic, topicName.getTopicName());
    }

}
