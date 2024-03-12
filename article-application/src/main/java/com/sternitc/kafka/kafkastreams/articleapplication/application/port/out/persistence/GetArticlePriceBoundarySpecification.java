package com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence;

public interface GetArticlePriceBoundarySpecification extends ArticlePriceBoundarySpecificationDao {

    ArticlePriceBoundarySpecificationDto get(String id);

}
