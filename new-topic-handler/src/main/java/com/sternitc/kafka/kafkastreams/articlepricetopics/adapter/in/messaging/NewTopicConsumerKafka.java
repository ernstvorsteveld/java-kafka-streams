package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.in.messaging;

import com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.in.NewTopicConsumer;
import com.sternitc.kafka.kafkastreams.articlepricetopics.application.domain.model.NewTopicCommand;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.in.NewTopicUseCase;
import org.springframework.kafka.annotation.KafkaListener;

public class NewTopicConsumerKafka implements NewTopicConsumer {

    public static final String newArticleTopic = "new_article_topic";

    private final NewTopicUseCase newTopicUseCase;

    public NewTopicConsumerKafka(NewTopicUseCase newTopicUseCase) {
        this.newTopicUseCase = newTopicUseCase;
    }

    @KafkaListener(id = newArticleTopic, topics = newArticleTopic, clientIdPrefix = "new-article-topic-client")
    public void listen(String topicName) {
        newTopic(new NewTopic(topicName));
    }

    @Override
    public void newTopic(NewTopicConsumer.NewTopic newTopic) {
        newTopicUseCase.newTopic(new NewTopicCommand(newTopic.topicName()));
    }
}
