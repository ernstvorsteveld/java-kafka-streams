package com.sternitc.kafka.kafkastreams.articlepricetopics;

import com.sternitc.kafka.kafkastreams.articlepricetopics.application.service.NewTopicService;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.in.NewTopicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePriceTopicConfiguration {

    @Bean
    public NewTopicUseCase newTopicUseCase() {
        return new NewTopicService();
    }
}
