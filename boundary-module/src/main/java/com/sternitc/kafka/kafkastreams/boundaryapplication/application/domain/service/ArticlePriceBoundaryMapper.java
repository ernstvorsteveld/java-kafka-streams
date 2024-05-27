package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.ArticlePriceThresholdDao;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceBoundary;

public class ArticlePriceThresholdMapper implements Mapper<
        ArticlePriceBoundary,
        ArticlePriceThresholdDao.ArticlePriceBoundaryDto> {

    @Override
    public ArticlePriceThresholdDao.ArticlePriceBoundaryDto to(
            ArticlePriceBoundary in) {
        return new ArticlePriceThresholdDao.ArticlePriceBoundaryDto(
                in.getArticleId(), in.getBoundaryType().toString(), in.getBoundary());
    }

    @Override
    public ArticlePriceBoundary from(
            ArticlePriceThresholdDao.ArticlePriceBoundaryDto in) {
        return null;
    }
}
