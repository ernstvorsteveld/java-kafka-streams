package com.sternitc.kafka.kafkastreams.articleprice.application;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.NewArticlePricePublisherPortKafka;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.NewArticlePublisherPortKafka;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticleNameHandler;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticleNameHandlerImpl;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.ArticlePriceMapper;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.NewArticlePricesService;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging.NewArticlePublisherPort;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.persistence.SaveTopicName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ArticlePriceApplicationConfiguration {

    @Bean
    public ArticleNameHandler articleNameHandler(
            GetTopicName getTopicName,
            SaveTopicName saveTopicName,
            NewArticlePublisherPort newArticlePublisherPort) {
        return new ArticleNameHandlerImpl(getTopicName, saveTopicName, newArticlePublisherPort);
    }

    @Bean
    public NewArticlePricesUseCase newArticlePricesUseCase(
            ArticlePriceMapper mapper,
            ArticleNameHandler articleNameHandler,
            NewArticlePricePublisherPort newArticlePricePublisherPort) {
        return new NewArticlePricesService(mapper, articleNameHandler, newArticlePricePublisherPort);
    }

    @Bean
    public ArticlePriceMapper articlePriceMapper() {
        return new ArticlePriceMapper();
    }

}
