package com.sternitc.kafka.kafkastreams.articleprice.application.port.in;

import java.util.Collection;

public interface NewArticlePricesUseCase {

    NewArticlePrices newArticlePrices(NewArticlePrices newPrices);

    record NewArticlePrice(String name, Integer price) {
    }

    record NewArticlePrices(Collection<NewArticlePrice> newPrices) {
    }
}
