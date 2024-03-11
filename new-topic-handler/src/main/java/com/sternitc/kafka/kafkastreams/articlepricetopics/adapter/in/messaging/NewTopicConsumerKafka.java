package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.in.messaging;

import com.sternitc.kafka.kafkastreams.articlepricetopics.application.domain.model.NewTopicCommand;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.in.NewTopicUseCase;
import org.springframework.kafka.annotation.KafkaListener;

public class NewTopicConsumerKafka {

    public static final String newArticleTopic = "new_article_topic";

    private final NewTopicUseCase newTopicUseCase;

    public NewTopicConsumerKafka(NewTopicUseCase newTopicUseCase) {
        this.newTopicUseCase = newTopicUseCase;
    }

    @KafkaListener(id = newArticleTopic, topics = newArticleTopic, clientIdPrefix = "new-article-topic-client")
    public void listen(String topicName) {
        newTopicUseCase.newTopic(new NewTopicCommand(topicName));
    }

}
