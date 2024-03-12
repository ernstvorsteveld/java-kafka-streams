package com.sternitc.kafka.kafkastreams.articleapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.NewArticleBoundaryCommand;

public interface NewArticleNotificationUseCase {

    Identity accept(NewArticleBoundaryCommand command);

    record Identity(String id) {
    }
}
