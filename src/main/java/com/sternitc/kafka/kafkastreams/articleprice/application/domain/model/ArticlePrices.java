package com.sternitc.kafka.kafkastreams.articleprice.application.domain.model;

import java.util.Collection;

public class ArticlePrices {

    private final Collection<ArticlePrice> prices;

    public ArticlePrices(Collection<ArticlePrice> prices) {
        this.prices = prices;
    }

}
