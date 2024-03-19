package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

import static com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.ArticlePriceThresholdMessagingConfiguration.priceBoundaryTopic;

public class NewPriceThresholdPublisherPortKafka implements NewPriceThresholdPublisherPort {

    private static final Logger logger = Logger.getLogger(NewPriceThresholdPublisherPortKafka.class.getName());

    public final KafkaTemplate<String, NewArticlePriceThresholdMessage> articlePriceBoundaryKafkaTemplate;

    public NewPriceThresholdPublisherPortKafka(
            KafkaTemplate<String, NewArticlePriceThresholdMessage> articlePriceThresholdKafkaTemplate) {
        this.articlePriceBoundaryKafkaTemplate = articlePriceThresholdKafkaTemplate;
    }

    @Override
    public void publish(NewArticlePriceThresholdEvent event) {
        logger.info("About to publish new article boundary event.");
        articlePriceBoundaryKafkaTemplate.send(
                priceBoundaryTopic,
                new NewArticlePriceThresholdMessage(event.articleId(), event.boundaryType(), event.value()));
    }

    public record NewArticlePriceThresholdMessage(String articleId, String type, int value) {
    }
}
