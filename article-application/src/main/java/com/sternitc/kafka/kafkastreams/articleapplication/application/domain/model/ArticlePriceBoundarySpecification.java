package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model;

public class ArticlePriceBoundarySpecification {

    private final String articleId;
    private final BoundaryType boundaryType;
    private final int boundary;

    public ArticlePriceBoundarySpecification(String articleId, BoundaryType boundaryType, int boundary) {
        this.articleId = articleId;
        this.boundaryType = boundaryType;
        this.boundary = boundary;
    }

    public static ArticlePriceBoundarySpecification from(NewArticleBoundaryCommand command) {
        return new ArticlePriceBoundarySpecification(
                command.getArticleId(), BoundaryType.valueOf(command.getBoundaryType()), command.getBoundary());
    }

    public String getArticleId() {
        return articleId;
    }

    public BoundaryType getBoundaryType() {
        return boundaryType;
    }

    public int getBoundary() {
        return boundary;
    }
}
