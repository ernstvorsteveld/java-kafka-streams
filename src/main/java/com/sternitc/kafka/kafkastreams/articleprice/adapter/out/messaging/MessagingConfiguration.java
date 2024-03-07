package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.GetTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.SaveTopicName;
import com.sternitc.kafka.kafkastreams.articleprice.application.domain.model.ArticlePrice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class MessagingConfiguration {

    @Bean
    public MessageProducerKafka messageProducerKafka(
            KafkaTemplate<String, ArticlePrice> articlePriceKafkaTemplate,
            KafkaTemplate<String, String> newTopicKafkaTemplate,
            ArticleTopicsService topics) {
        return new MessageProducerKafka(
                articlePriceKafkaTemplate,
                newTopicKafkaTemplate,
                topics);
    }

    @Bean
    public ArticleTopicsService articleTopics(
            SaveTopicName saveTopicName,
            GetTopicName getTopicName) {
        return new ArticleTopicsService(saveTopicName, getTopicName);
    }
}
