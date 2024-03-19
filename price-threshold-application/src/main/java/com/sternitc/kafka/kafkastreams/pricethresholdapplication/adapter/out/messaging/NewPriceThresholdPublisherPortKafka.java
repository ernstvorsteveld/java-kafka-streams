package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.messaging.NewPriceThresholdPublisherPort;
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
        logger.info("About to publish new article boundary event.");
        boolean send = streamBridge.send(
                priceBoundaryTopic,
                new NewArticlePriceThresholdMessage(event.articleId(), event.boundaryType(), event.value()));
        System.out.println(send);
    }

    public record NewArticlePriceThresholdMessage(String articleId, String type, int value) {
    }
}
