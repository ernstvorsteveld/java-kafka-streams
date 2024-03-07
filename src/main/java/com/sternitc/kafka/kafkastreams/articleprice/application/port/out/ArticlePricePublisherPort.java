package com.sternitc.kafka.kafkastreams.articleprice.application.port.out;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;

public interface ArticlePricePublisherPort {

    void publish(ArticlePrice articlePrice);
}
