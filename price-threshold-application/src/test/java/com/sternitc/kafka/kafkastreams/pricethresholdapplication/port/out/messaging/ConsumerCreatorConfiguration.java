package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka.NewArticlePriceThresholdMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ConsumerCreatorConfiguration {

    public Map<String, NewArticlePriceThresholdMessage> messages = new HashMap<>();

    @Bean
    public Map<String, NewArticlePriceThresholdMessage> msg() {
        return messages;
    }

    @Bean
    public Consumer<NewArticlePriceThresholdMessage> newPriceThresholdConsumer() {
        return (value) -> messages.put(value.articleId(), value);
    }

}
