package com.sternitc.kafka.thresholdapplication;

public record ArticlePrice(String articleId, String boundaryType, int value) {
}
