package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging;

public interface NewArticlePublisherPort {

    void publish(TopicNameEvent event);

    record NewArticleMessage(String name){}

    record TopicNameEvent(TopicName topicName, NewArticleMessage message) {
    }

    record TopicName(String name) {
        @Override
        public String name() {
            return TOPIC_PREFIX + name.toLowerCase();
        }
    }

    String TOPIC_PREFIX = "article_price_";
}
