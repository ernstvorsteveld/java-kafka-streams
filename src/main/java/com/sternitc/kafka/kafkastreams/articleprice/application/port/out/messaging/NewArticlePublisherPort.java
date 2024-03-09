package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging;

public interface NewArticlePublisherPort {

    void publish(TopicNameMessage name);

    record NewArticleMessage(String name){}

    record TopicNameMessage(TopicName topicName, NewArticleMessage message) {
    }

    record TopicName(String name) {
        @Override
        public String name() {
            return TOPIC_PREFIX + name.toLowerCase();
        }
    }

    String TOPIC_PREFIX = "article_price_";
}
