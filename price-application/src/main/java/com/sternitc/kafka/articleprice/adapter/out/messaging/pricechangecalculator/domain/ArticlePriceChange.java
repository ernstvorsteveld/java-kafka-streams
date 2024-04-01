package com.sternitc.kafka.articleprice.adapter.out.messaging.pricechangecalculator.domain;

public record ArticlePriceChange(
        String articleId,
        ArticlePrice old,
        ArticlePrice current,
        PriceChangeType changeType) {
}
