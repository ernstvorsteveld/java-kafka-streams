package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;

public record ArticleThreshold(String thresholdId, int price, String boundary) {
}
