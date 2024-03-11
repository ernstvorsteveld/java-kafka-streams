package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging;

public interface NewArticlePricePublisherPort {

    void publish(NewPriceEvent event);

    record NewPriceEvent(NewArticlePublisherPort.TopicName topicName, String name, int price) {
    }

}
