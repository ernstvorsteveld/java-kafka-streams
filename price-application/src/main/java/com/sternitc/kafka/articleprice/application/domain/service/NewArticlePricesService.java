package com.sternitc.kafka.articleprice.application.domain.service;

import com.sternitc.kafka.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import com.sternitc.kafka.articleprice.application.port.out.messaging.NewArticlePricePublisherPort.NewArticlePriceEvent;

public class NewArticlePricesService implements NewArticlePricesUseCase {

    private final ArticlePriceMapper mapper;
    private final NewArticlePricePublisherPort newArticlePricePublisherPort;

    public NewArticlePricesService(
            ArticlePriceMapper mapper,
            NewArticlePricePublisherPort newArticlePricePublisherPort) {
        this.mapper = mapper;
        this.newArticlePricePublisherPort = newArticlePricePublisherPort;
    }

    @Override
    public void newArticlePrices(NewArticlePrices newPrices) {
        newPrices.newPrices()
                .stream()
                .parallel()
                .map(mapper::to)
                .forEach(this::publishMessage);
    }

    private void publishMessage(ArticlePrice articlePrice) {
        newArticlePricePublisherPort.publish(
                new NewArticlePriceEvent(articlePrice.getName(), articlePrice.getPrice()));
    }

}
