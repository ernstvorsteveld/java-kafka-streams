package com.sternitc.kafka.kafkastreams.articleprice.application.port.in;

import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticleNameHandler;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticleNameHandlerImpl;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticlePriceMapper;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.NewArticlePricesService;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePricePortInConfiguration {

    @Bean
    public NewArticlePricesUseCase newArticlePricesUseCase(
            ArticlePriceMapper mapper,
            ArticleNameHandler articleNameHandler,
            NewArticlePricePublisherPort newArticlePricePublisherPort) {
        return new NewArticlePricesService(mapper, articleNameHandler, newArticlePricePublisherPort);
    }

}
