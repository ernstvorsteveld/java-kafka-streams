package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.inmemory.InMemoryTopicNameStoreAdapter;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.DeleteTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!mongodb")
public class PersistenceInMemoryConfiguration {

    @Bean
    public SaveTopicName saveTopicName(
            InMemoryTopicNameStoreAdapter inMemoryTopicNameStore) {
        return inMemoryTopicNameStore;
    }

    @Bean
    public GetTopicName getTopicName(
            InMemoryTopicNameStoreAdapter inMemoryTopicNameStore) {
        return inMemoryTopicNameStore;
    }

    @Bean
    DeleteTopicName deleteTopicName(
            InMemoryTopicNameStoreAdapter inMemoryTopicNameStore) {
        return inMemoryTopicNameStore;
    }

    @Bean
    public InMemoryTopicNameStoreAdapter inMemoryTopicNameStore() {
        return new InMemoryTopicNameStoreAdapter();
    }
}
