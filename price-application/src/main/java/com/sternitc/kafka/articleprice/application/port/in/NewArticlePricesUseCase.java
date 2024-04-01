package com.sternitc.kafka.articleprice.application.port.in;

import java.util.Collection;

public interface NewArticlePricesUseCase {

    void newArticlePrices(NewArticlePrices newPrices);

    record NewArticlePrice(String name, Integer price) {
    }

    record NewArticlePrices(Collection<NewArticlePrice> newPrices) {
    }
}
