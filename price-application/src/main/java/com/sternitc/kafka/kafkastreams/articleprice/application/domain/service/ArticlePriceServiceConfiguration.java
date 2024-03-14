package com.sternitc.kafka.kafkastreams.articleprice.application.domain.service;

import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePriceServiceConfiguration {

    @Bean
    public ArticleNameHandler articleNameHandler(
            GetTopicName getTopicName,
            SaveTopicName saveTopicName,
            NewArticlePublisherPort newArticlePublisherPort) {
        return new ArticleNameHandlerImpl(getTopicName, saveTopicName, newArticlePublisherPort);
    }

    @Bean
    public ArticlePriceMapper articlePriceMapper() {
        return new ArticlePriceMapper();
    }

}
