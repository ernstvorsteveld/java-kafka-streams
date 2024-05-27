package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence;

public interface GetArticlePriceBoundary extends ArticlePriceBoundaryDao {

    ArticlePriceBoundaryDto get(String id);

}
