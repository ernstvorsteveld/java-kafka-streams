package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.logging.Logger;

public class NewPriceBoundaryPublisherPortKafka implements NewPriceThresholdPublisherPort {

    private static final Logger logger = Logger.getLogger(NewPriceBoundaryPublisherPortKafka.class.getName());
    private final StreamBridge streamBridge;
    private final String priceBoundaryTopic;

    public NewPriceBoundaryPublisherPortKafka(StreamBridge streamBridge, String priceBoundaryTopic) {
        this.streamBridge = streamBridge;
        this.priceBoundaryTopic = priceBoundaryTopic;
    }

    @Override
    public void publish(NewArticlePriceBoundaryEvent event) {
        logger.info(String.format("About to publish new article price boundary event to topic %s.", priceBoundaryTopic));
        NewArticlePriceBoundaryMessage message = new NewArticlePriceBoundaryMessage(
                event.companyId(), event.userId(), event.articleId(), event.boundaryType(), event.value());
        streamBridge.send(priceBoundaryTopic, message);
    }

    public record NewArticlePriceBoundaryMessage(
            String companyId,
            String userId,
            String articleId,
            String type,
            int value) {
    }
}
