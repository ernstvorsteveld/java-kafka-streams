package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

public class NewArticlePricePublisherPortKafka implements NewArticlePricePublisherPort {

    private final KafkaTemplate<String, NewPriceMessage> articlePriceKafkaTemplate;

    public NewArticlePricePublisherPortKafka(
            KafkaTemplate<String, NewPriceMessage> articlePriceKafkaTemplate) {
        this.articlePriceKafkaTemplate = articlePriceKafkaTemplate;
    }

    @Override
    public void publish(NewPriceEvent price) {
        articlePriceKafkaTemplate.send(
                price.topicName().name(),
                new NewPriceMessage(price.name(), price.price()));
    }

    public record NewPriceMessage(String name, int price) {
    }

}
