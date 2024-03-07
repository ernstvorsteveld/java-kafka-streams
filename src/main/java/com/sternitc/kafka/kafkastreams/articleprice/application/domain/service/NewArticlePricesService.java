package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePriceMapper;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.ArticlePricePublisherPort;

import java.util.List;

public class NewArticlePricesService implements NewArticlePricesUseCase {

    private final ArticlePriceMapper mapper;
    private final ArticlePricePublisherPort publisherPort;

    public NewArticlePricesService(ArticlePriceMapper mapper, ArticlePricePublisherPort publisherPort) {
        this.mapper = mapper;
        this.publisherPort = publisherPort;
    }

    @Override
    public int newArticlePrices(NewArticlePrices newPrices) {
        ArticlePricePublisher publisher = new ArticlePricePublisher(publisherPort);
        List<ArticlePrice> articlePrices =
                newPrices.newPrices()
                        .stream()
                        .map(mapper::to)
                        .map(publisher::publish)
                        .toList();
        return articlePrices.size();
    }

    public static class ArticlePricePublisher {

        private final ArticlePricePublisherPort port;

        public ArticlePricePublisher(ArticlePricePublisherPort port) {
            this.port = port;
        }

        public ArticlePrice publish(ArticlePrice articlePrice) {
            this.port.publish(articlePrice);
            return articlePrice;
        }
    }
}
