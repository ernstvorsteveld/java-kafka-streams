package com.sternitc.kafka.kafkastreams.articleprice.application;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.ArticleTopicsService;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.MessageProducerKafka;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePriceMapper;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.service.NewArticlePricesService;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.kafkastreams.articleprice.application.port.out.ArticlePricePublisherPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ArticlePriceApplicationConfiguration {

    @Bean
    public ArticlePricePublisherPort publisherPort(
            KafkaTemplate<String, ArticlePrice> articlePriceKafkaTemplate,
            KafkaTemplate<String, String> newTopicKafkaTemplate,
            ArticleTopicsService topics) {
        return new MessageProducerKafka(
                articlePriceKafkaTemplate,
                newTopicKafkaTemplate,
                topics);
    }

    @Bean
    public NewArticlePricesUseCase newArticlePricesUseCase(
            ArticlePriceMapper mapper,
            ArticlePricePublisherPort publisherPort) {
        return new NewArticlePricesService(mapper, publisherPort);
    }

    @Bean
    public ArticlePriceMapper articlePriceMapper() {
        return new ArticlePriceMapper();
    }
}
