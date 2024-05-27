package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging.NewPriceBoundaryPublisherPortKafka.NewArticlePriceBoundaryMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Configuration
public class ConsumerCreatorConfiguration {

    public Map<String, NewArticlePriceBoundaryMessage> messages = new HashMap<>();

    @Bean
    public Map<String, NewArticlePriceBoundaryMessage> msg() {
        return messages;
    }

    @Bean
    public Consumer<NewArticlePriceBoundaryMessage> newPriceThresholdConsumer() {
        return (value) -> messages.put(value.articleId(), value);
    }

}
