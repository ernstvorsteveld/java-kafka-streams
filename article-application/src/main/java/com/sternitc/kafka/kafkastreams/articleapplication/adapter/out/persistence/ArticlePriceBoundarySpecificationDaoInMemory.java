package com.sternitc.kafka.kafkastreams.articleapplication.adapter.out.persistence;

import com.sternitc.kafka.kafkastreams.articleapplication.port.out.persistence.GetArticlePriceBoundarySpecification;
import com.sternitc.kafka.kafkastreams.articleapplication.port.out.persistence.SaveArticlePriceBoundarySpecification;

import java.util.HashMap;
import java.util.Map;

public class ArticlePriceBoundarySpecificationDaoInMemory
        implements SaveArticlePriceBoundarySpecification, GetArticlePriceBoundarySpecification {

    private final Map<String, ArticlePriceBoundarySpecificationDto> articlePrices = new HashMap<>();

    @Override
    public String save(ArticlePriceBoundarySpecificationDto dto) {
        articlePrices.put(dto.articleId(), dto);
        return dto.articleId();
    }

    @Override
    public ArticlePriceBoundarySpecificationDto get(String id) {
        return articlePrices.get(id);
    }
}
