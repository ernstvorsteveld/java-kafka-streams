package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.ArticlePriceBoundarySpecification;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.ArticlePriceBoundarySpecificationDao;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.SaveArticlePriceBoundarySpecification;

public class ArticlePriceBoundarySpecificationMapper implements Mapper<
        ArticlePriceBoundarySpecification,
        SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto> {

    @Override
    public ArticlePriceBoundarySpecificationDao.ArticlePriceBoundarySpecificationDto to(
            ArticlePriceBoundarySpecification in) {
        return new SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto(
                in.getArticleId(), in.getBoundaryType().toString(), in.getBoundary());
    }

    @Override
    public ArticlePriceBoundarySpecification from(
            SaveArticlePriceBoundarySpecification.ArticlePriceBoundarySpecificationDto in) {
        return null;
    }
}
