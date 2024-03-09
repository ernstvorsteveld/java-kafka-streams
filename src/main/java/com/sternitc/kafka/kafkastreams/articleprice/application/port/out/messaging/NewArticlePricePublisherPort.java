package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging;

public interface NewArticlePricePublisherPort {

    void publish(NewPrice price);

    record NewPrice(NewArticlePublisherPort.TopicName topicName, String name, int price) {
    }

    record NewPriceMessage(String name, int price) {
    }

}
