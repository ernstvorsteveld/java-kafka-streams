package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.GetArticlePriceBoundary;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.SaveArticlePriceBoundary;

import java.util.HashMap;
import java.util.Map;

public class ArticlePriceBoundaryDaoInMemory
        implements SaveArticlePriceBoundary, GetArticlePriceBoundary {

    private final Map<String, ArticlePriceBoundaryDto> articlePrices = new HashMap<>();

    @Override
    public String save(ArticlePriceBoundaryDto dto) {
        articlePrices.put(dto.articleId(), dto);
        return dto.articleId();
    }

    @Override
    public ArticlePriceBoundaryDto get(String id) {
        return articlePrices.get(id);
    }
}
