package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.GetTopic;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.SaveTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class InMemoryPersistenceConfiguration {

    @Bean
    public GetTopic getTopic() {
        return topicInMemory();
    }

    @Bean
    public SaveTopic saveTopic() {
        return topicInMemory();
    }

    @Bean
    public TopicInMemory topicInMemory() {
        return new TopicInMemory();
    }
}
