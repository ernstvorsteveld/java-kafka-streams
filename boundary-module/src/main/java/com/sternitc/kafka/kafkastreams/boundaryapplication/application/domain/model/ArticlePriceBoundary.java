package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model;

public class ArticlePriceBoundary {

    private final String articleId;
    private final String companyId;
    private final String userId;
    private final BoundaryType boundaryType;
    private final int boundary;

    public ArticlePriceBoundary(String articleId, String companyId, String userid, BoundaryType boundaryType, int boundary) {
        this.articleId = articleId;
        this.companyId = companyId;
        this.userId = userid;
        this.boundaryType = boundaryType;
        this.boundary = boundary;
    }

    public static ArticlePriceBoundary from(NewPriceBoundaryCommand command) {
        return new ArticlePriceBoundary(
                command.getArticleId(), command.getCompanyId(), command.getUserId(), BoundaryType.valueOf(command.getBoundaryType()), command.getBoundary());
    }

    public String getArticleId() {
        return articleId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getUserId() {
        return userId;
    }

    public BoundaryType getBoundaryType() {
        return boundaryType;
    }

    public int getBoundary() {
        return boundary;
    }
}
