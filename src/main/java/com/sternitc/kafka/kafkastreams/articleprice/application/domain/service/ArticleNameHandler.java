package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;

public interface ArticleNameHandler {

    void handle(ArticlePrice articlePrice);
}
