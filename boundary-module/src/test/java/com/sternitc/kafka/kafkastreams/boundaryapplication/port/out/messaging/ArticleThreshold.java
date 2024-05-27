package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

public record ArticleThreshold(String thresholdId, int price, String boundary) {
}
