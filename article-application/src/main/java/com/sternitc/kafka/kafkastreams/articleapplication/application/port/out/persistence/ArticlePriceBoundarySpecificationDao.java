package com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence;

public interface ArticlePriceBoundarySpecificationDao {

    record ArticlePriceBoundarySpecificationDto(String articleId, String boundaryTyp, int value) {
    }
}
