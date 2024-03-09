package com.sternitc.kafka.kafkastreams.articlepricetopics.port.in;


import com.sternitc.kafka.kafkastreams.articlepricetopics.application.domain.model.NewTopicCommand;

public interface NewTopicUseCase {

    void newTopic(NewTopicCommand command);

}
