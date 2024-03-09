package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.in;

public interface NewTopicConsumer {

    void newTopic(NewTopic newTopic);

    record NewTopic(String topicName) {
    }

}
