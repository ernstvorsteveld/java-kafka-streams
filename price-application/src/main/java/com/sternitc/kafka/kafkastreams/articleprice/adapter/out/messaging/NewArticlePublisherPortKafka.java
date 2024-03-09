package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

public class NewArticlePublisherPortKafka implements NewArticlePublisherPort {

    private static final Logger logger = Logger.getLogger(NewArticlePublisherPortKafka.class.getName());

    private final KafkaTemplate<String, String> newTopicKafkaTemplate;

    public NewArticlePublisherPortKafka(KafkaTemplate<String, String> newTopicKafkaTemplate) {
        this.newTopicKafkaTemplate = newTopicKafkaTemplate;
    }

    @Override
    public void publish(TopicNameMessage message) {
        logger.info(String.format("Handle new topic creation, name: %s", message.topicName().name()));
        newTopicKafkaTemplate.send(ArticlePriceProducerConfiguration.newArticleTopic, message.message().name());
    }

}
