package com.sternitc.kafka.articleprice.adapter.out.messaging.newarticleprice;

import com.sternitc.kafka.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

public class NewArticlePricePublisherPortKafka implements NewArticlePricePublisherPort {

    private final KafkaTemplate<String, NewArticlePriceEvent> articlePriceKafkaTemplate;
    private final String newArticlePriceTopicName;

    public NewArticlePricePublisherPortKafka(
            String newArticlePriceTopicName,
            KafkaTemplate<String, NewArticlePriceEvent> articlePriceKafkaTemplate) {
        this.newArticlePriceTopicName = newArticlePriceTopicName;
        this.articlePriceKafkaTemplate = articlePriceKafkaTemplate;
    }

    @Override
    public void publish(NewArticlePriceEvent newArticlePriceEvent) {
        articlePriceKafkaTemplate.send(
                newArticlePriceTopicName,
                newArticlePriceEvent.name(),
                newArticlePriceEvent);
    }

}
