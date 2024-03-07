package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.inmemory;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.SaveTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("no-mongodb")
public class PersistenceInMemoryConfiguration {

    @Bean
    public SaveTopicName saveTopicName(
            InMemoryTopicNameStoreImpl inMemoryTopicNameStore) {
        return inMemoryTopicNameStore;
    }

    @Bean
    public GetTopicName getTopicName(
            InMemoryTopicNameStoreImpl inMemoryTopicNameStore) {
        return inMemoryTopicNameStore;
    }

    @Bean
    public InMemoryTopicNameStoreImpl inMemoryTopicNameStore() {
        return new InMemoryTopicNameStoreImpl();
    }
}
