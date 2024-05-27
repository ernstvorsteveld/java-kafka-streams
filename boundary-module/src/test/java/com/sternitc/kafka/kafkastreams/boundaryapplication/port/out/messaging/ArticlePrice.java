package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

public record ArticlePrice(String articleId, String boundaryType, int value) {
}
