package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

public interface ArticlePriceBoundaryDao {

    record ArticlePriceBoundaryDto(String articleId, String thresholdType, int value) {
    }
}
