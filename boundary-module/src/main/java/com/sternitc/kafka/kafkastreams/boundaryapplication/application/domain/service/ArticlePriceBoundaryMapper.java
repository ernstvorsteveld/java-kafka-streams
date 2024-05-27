package com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.ArticlePriceBoundaryDao;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.ArticlePriceBoundary;

public class ArticlePriceBoundaryMapper implements Mapper<
        ArticlePriceBoundary,
        ArticlePriceBoundaryDao.ArticlePriceBoundaryDto> {

    @Override
    public ArticlePriceBoundaryDao.ArticlePriceBoundaryDto to(
            ArticlePriceBoundary in) {
        return new ArticlePriceBoundaryDao.ArticlePriceBoundaryDto(
                in.getArticleId(), in.getBoundaryType().toString(), in.getBoundary());
    }

    @Override
    public ArticlePriceBoundary from(
            ArticlePriceBoundaryDao.ArticlePriceBoundaryDto in) {
        return null;
    }
}
