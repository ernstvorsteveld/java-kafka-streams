package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model;

public class NewPriceBoundaryCommand {

    private final String articleId;
    private final String companyId;
    private final String userId;
    private final String boundaryType;
    private final int boundary;

    public NewPriceBoundaryCommand(String articleId, String companyId, String userId, String boundaryType, int boundary) {
        this.articleId = articleId;
        this.companyId = companyId;
        this.userId =  userId;
        this.boundaryType = boundaryType;
        this.boundary = boundary;
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

    public String getBoundaryType() {
        return boundaryType;
    }

    public int getBoundary() {
        return boundary;
    }
}
