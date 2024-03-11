package com.sternitc.kafka.kafkastreams.articlepricetopics.application.service;

import com.sternitc.kafka.kafkastreams.articlepricetopics.application.domain.model.NewTopicCommand;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.in.NewTopicUseCase;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.GetTopic;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.SaveTopic;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.TopicPersistence;

import java.util.logging.Logger;

public class NewTopicService implements NewTopicUseCase {

    private static final Logger logger = Logger.getLogger(NewTopicService.class.getName());

    private final GetTopic getTopic;
    private final SaveTopic saveTopic;

    public NewTopicService(GetTopic getTopic, SaveTopic saveTopic) {
        this.getTopic = getTopic;
        this.saveTopic = saveTopic;
    }

    @Override
    public void newTopic(NewTopicCommand command) {
        TopicPersistence.TopicName topicName = new TopicPersistence.TopicName(command.getTopicName());
        TopicPersistence.Topic topic = getTopic.get(topicName);
        if (topic != null) {
            logger.info(String.format("Received a topic that already exists %s", command.getTopicName()));
            return;
        }
        saveTopic.save(topic);
    }
}
