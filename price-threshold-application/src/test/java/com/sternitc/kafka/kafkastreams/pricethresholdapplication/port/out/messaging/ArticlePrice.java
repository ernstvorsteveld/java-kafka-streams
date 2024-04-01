package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;

public record ArticlePrice(String articleId, String boundaryType, int value) {
}
