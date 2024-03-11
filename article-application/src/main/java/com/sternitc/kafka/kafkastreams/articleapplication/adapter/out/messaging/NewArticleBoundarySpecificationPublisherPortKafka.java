package com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleapplication.port.out.messaging.NewArticleBoundarySpecificationPublisherPort;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Logger;

import static com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.messaging.ArticlePriceBoundarySpecificationMessagingConfiguration.priceBoundaryTopic;

public class NewArticleBoundarySpecificationPublisherPortKafka implements NewArticleBoundarySpecificationPublisherPort {

    private static final Logger logger = Logger.getLogger(NewArticleBoundarySpecificationPublisherPortKafka.class.getName());

    public final KafkaTemplate<String, NewArticlePriceBoundarySpecificationMessage> articlePriceBoundaryKafkaTemplate;

    public NewArticleBoundarySpecificationPublisherPortKafka(
            KafkaTemplate<String, NewArticlePriceBoundarySpecificationMessage> articlePriceBoundaryKafkaTemplate) {
        this.articlePriceBoundaryKafkaTemplate = articlePriceBoundaryKafkaTemplate;
    }

    @Override
    public void publish(NewArticleBoundarySpecificationEvent event) {
        logger.info("About to publish new article boundary event.");
        articlePriceBoundaryKafkaTemplate.send(
                priceBoundaryTopic,
                new NewArticlePriceBoundarySpecificationMessage(event.articleId(), event.boundaryType(), event.value()));
    }

    public record NewArticlePriceBoundarySpecificationMessage(String articleId, String type, int value) {
    }
}
