package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.logging.Logger;

public class NewPriceThresholdPublisherPortKafka implements NewPriceThresholdPublisherPort {

    private static final Logger logger = Logger.getLogger(NewPriceThresholdPublisherPortKafka.class.getName());
    private final StreamBridge streamBridge;
    private final String priceBoundaryTopic;

    public NewPriceThresholdPublisherPortKafka(StreamBridge streamBridge, String priceBoundaryTopic) {
        this.streamBridge = streamBridge;
        this.priceBoundaryTopic = priceBoundaryTopic;
    }

    @Override
    public void publish(NewArticlePriceThresholdEvent event) {
        logger.info(String.format("About to publish new article price boundary event to topic %s.", priceBoundaryTopic));
        NewArticlePriceThresholdMessage message = new NewArticlePriceThresholdMessage(
                event.companyId(), event.userId(), event.articleId(), event.boundaryType(), event.value());
        streamBridge.send(priceBoundaryTopic, message);
    }

    public record NewArticlePriceThresholdMessage(
            String companyId,
            String userId,
            String articleId,
            String type,
            int value) {
    }
}
