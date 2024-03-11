package com.sternitc.kafka.kafkastreams.articleapplication.port.out.persistence;

public interface GetArticlePriceBoundarySpecification extends ArticlePriceBoundarySpecificationDao {

    ArticlePriceBoundarySpecificationDto get(String id);

}
