package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

public interface SaveArticlePriceBoundary extends ArticlePriceBoundaryDao {

    String save(ArticlePriceBoundaryDto dto);

}
