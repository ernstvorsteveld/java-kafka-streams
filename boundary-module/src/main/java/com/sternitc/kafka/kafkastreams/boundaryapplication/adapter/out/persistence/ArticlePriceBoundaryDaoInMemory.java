package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.GetArticlePriceThreshold;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.SaveArticlePriceThreshold;

import java.util.HashMap;
import java.util.Map;

public class ArticlePriceThresholdDaoInMemory
        implements SaveArticlePriceThreshold, GetArticlePriceThreshold {

    private final Map<String, ArticlePriceThresholdDto> articlePrices = new HashMap<>();

    @Override
    public String save(ArticlePriceThresholdDto dto) {
        articlePrices.put(dto.articleId(), dto);
        return dto.articleId();
    }

    @Override
    public ArticlePriceThresholdDto get(String id) {
        return articlePrices.get(id);
    }
}
