package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model;

public class ArticlePriceThreshold {

    private final String articleId;
    private final ThresholdType thresholdType;
    private final int boundary;

    public ArticlePriceThreshold(String articleId, ThresholdType thresholdType, int boundary) {
        this.articleId = articleId;
        this.thresholdType = thresholdType;
        this.boundary = boundary;
    }

    public static ArticlePriceThreshold from(NewPriceThresholdCommand command) {
        return new ArticlePriceThreshold(
                command.getArticleId(), ThresholdType.valueOf(command.getBoundaryType()), command.getBoundary());
    }

    public String getArticleId() {
        return articleId;
    }

    public ThresholdType getBoundaryType() {
        return thresholdType;
    }

    public int getBoundary() {
        return boundary;
    }
}
