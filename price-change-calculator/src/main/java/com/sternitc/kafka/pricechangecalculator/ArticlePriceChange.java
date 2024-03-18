package com.sternitc.kafka.pricechangecalculator;

public record ArticlePriceChange(
        String articleId,
        ArticlePrice old,
        ArticlePrice current,
        PriceChangeType changeType) {
}
