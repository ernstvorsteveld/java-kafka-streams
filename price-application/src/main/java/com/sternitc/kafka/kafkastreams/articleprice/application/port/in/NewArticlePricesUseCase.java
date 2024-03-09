package com.sternitc.kafka.kafkastreams.articleprice.application.port.in;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;

import java.util.Collection;
import java.util.List;

public interface NewArticlePricesUseCase {

    NewArticlePrices newArticlePrices(NewArticlePrices newPrices);

    record NewArticlePrice(String name, Integer price) {
    }
    record NewArticlePrices(Collection<NewArticlePrice> newPrices) {
    }
}
