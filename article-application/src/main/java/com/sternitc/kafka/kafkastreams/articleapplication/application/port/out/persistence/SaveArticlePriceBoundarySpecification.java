package com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence;

public interface SaveArticlePriceBoundarySpecification extends ArticlePriceBoundarySpecificationDao {

    String save(ArticlePriceBoundarySpecificationDto dto);

}
