package com.sternitc.kafka.kafkastreams.articleprice.application.port.in;

import java.util.Collection;

public interface NewArticlePricesUseCase {

    int newArticlePrices(NewArticlePrices newPrices);

    record NewArticlePrice(String name, Integer price) {
    }
    record NewArticlePrices(Collection<NewArticlePrice> newPrices) {
        @Override
        public Collection<NewArticlePrice> newPrices() {
            return newPrices;
        }
    }
}
