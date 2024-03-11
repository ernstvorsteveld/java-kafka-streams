package com.sternitc.kafka.kafkastreams.articlepricetopics.adapter;

import com.sternitc.kafka.kafkastreams.articlepricetopics.adapter.in.messaging.NewTopicConsumerKafka;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.in.NewTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class AdapterKafkaConfiguration {

    @Bean
    public NewTopicConsumerKafka newTopicConsumerKafka(
            NewTopicUseCase newTopicUseCase) {
        return new NewTopicConsumerKafka(newTopicUseCase);
    }

}
