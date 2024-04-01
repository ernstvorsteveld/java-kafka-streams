package com.sternitc.kafka.pricechangecalculator.application.domain;

public record ArticlePriceChange(
        String articleId,
        ArticlePrice old,
        ArticlePrice current,
        PriceChangeType changeType) {
}
