package com.sternitc.kafka.kafkastreams.articlepricetopics.application.domain.model;

public class NewTopicCommand {

    private final String topicName;

    public NewTopicCommand(String topicName) {
        if (topicName == null || topicName.isEmpty() || topicName.isBlank()) {
            throw new NoTopicNameException();
        }
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }
}
