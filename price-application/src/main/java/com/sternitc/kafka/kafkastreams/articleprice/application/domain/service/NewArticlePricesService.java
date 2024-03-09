package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;

public class NewArticlePricesService implements NewArticlePricesUseCase {

    private final ArticlePriceMapper mapper;
    private final NewArticlePricePublisherPort newArticlePricePublisherPort;
    private final ArticleNameHandler articleNameHandler;

    public NewArticlePricesService(
            ArticlePriceMapper mapper,
            ArticleNameHandler articleNameHandler,
            NewArticlePricePublisherPort newArticlePricePublisherPort) {
        this.mapper = mapper;
        this.articleNameHandler = articleNameHandler;
        this.newArticlePricePublisherPort = newArticlePricePublisherPort;
    }

    @Override
    public NewArticlePrices newArticlePrices(NewArticlePrices newPrices) {
        return new NewArticlePrices(
                newPrices.newPrices()
                        .stream()
                        .parallel()
                        .map(mapper::to)
                        .map(this::handleName)
                        .map(this::publishMessage)
                        .map(mapper::from)
                        .toList());
    }

    public ArticlePrice publishMessage(ArticlePrice articlePrice) {
        newArticlePricePublisherPort.publish(
                new NewArticlePricePublisherPort.NewPrice(
                        new NewArticlePublisherPort.TopicName(articlePrice.getName()),
                        articlePrice.getName(),
                        articlePrice.getPrice()));
        return articlePrice;
    }

    public ArticlePrice handleName(ArticlePrice articlePrice) {
        articleNameHandler.handle(articlePrice);
        return articlePrice;
    }

}
