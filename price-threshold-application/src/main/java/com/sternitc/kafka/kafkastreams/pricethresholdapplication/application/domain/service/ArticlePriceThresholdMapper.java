package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.service;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.ArticlePriceThreshold;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.ArticlePriceThresholdDao;

public class ArticlePriceThresholdMapper implements Mapper<
        ArticlePriceThreshold,
        ArticlePriceThresholdDao.ArticlePriceThresholdDto> {

    @Override
    public ArticlePriceThresholdDao.ArticlePriceThresholdDto to(
            ArticlePriceThreshold in) {
        return new ArticlePriceThresholdDao.ArticlePriceThresholdDto(
                in.getArticleId(), in.getBoundaryType().toString(), in.getBoundary());
    }

    @Override
    public ArticlePriceThreshold from(
            ArticlePriceThresholdDao.ArticlePriceThresholdDto in) {
        return null;
    }
}
