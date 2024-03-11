package com.sternitc.kafka.kafkastreams.articleapplication.port.in;

import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.NewArticleBoundaryCommand;

public interface NewArticleNotificationUseCase {

    Identity accept(NewArticleBoundaryCommand command);

    record Identity(String id) {
    }
}
