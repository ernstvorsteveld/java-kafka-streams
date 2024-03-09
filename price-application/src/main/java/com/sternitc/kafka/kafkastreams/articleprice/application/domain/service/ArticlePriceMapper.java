package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.Mapper;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;

public class ArticlePriceMapper implements Mapper<NewArticlePricesUseCase.NewArticlePrice, ArticlePrice> {

    @Override
    public ArticlePrice to(NewArticlePricesUseCase.NewArticlePrice in) {
        return new ArticlePrice(in.name(), in.price());
    }

    @Override
    public NewArticlePricesUseCase.NewArticlePrice from(ArticlePrice in) {
        return null;
    }
}
