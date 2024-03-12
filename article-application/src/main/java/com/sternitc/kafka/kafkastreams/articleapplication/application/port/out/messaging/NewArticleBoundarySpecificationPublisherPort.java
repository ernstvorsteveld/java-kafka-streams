package com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.messaging;

public interface NewArticleBoundarySpecificationPublisherPort {

    void publish(NewArticleBoundarySpecificationEvent event);

    record NewArticleBoundarySpecificationEvent(String articleId, String boundaryType, int value) {
    }

}
