package com.sternitc.kafka.kafkastreams.articleprice.application.domain.model;

import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;

import java.util.UUID;

public class ArticlePriceMapper implements Mapper<NewArticlePricesUseCase.NewArticlePrice, ArticlePrice> {

    @Override
    public ArticlePrice to(NewArticlePricesUseCase.NewArticlePrice in) {
        ArticlePrice.ArticlePriceBuilder builder = new ArticlePrice.ArticlePriceBuilder();
        builder.name(in.name());
        builder.price(in.price());
        return builder.build();
    }

    @Override
    public NewArticlePricesUseCase.NewArticlePrice from(ArticlePrice in) {
        return null;
    }
}
