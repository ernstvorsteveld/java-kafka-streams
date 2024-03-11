package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model;

public class NewArticleBoundaryCommand {

    private final String articleId;
    private final String BoundaryType;
    private final int boundary;

    public NewArticleBoundaryCommand(String articleId, String boundaryType, int boundary) {
        this.articleId = articleId;
        BoundaryType = boundaryType;
        this.boundary = boundary;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getBoundaryType() {
        return BoundaryType;
    }

    public int getBoundary() {
        return boundary;
    }
}
