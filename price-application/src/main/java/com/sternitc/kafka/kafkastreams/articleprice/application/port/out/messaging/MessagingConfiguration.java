package com.sternitc.kafka.kafkastreams.articleprice.application.port.out.messaging;

import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.NewArticlePricePublisherPortKafka;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.NewArticlePublisherPortKafka;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Profile("kafka")
public class MessagingConfiguration {

    @Bean
    public NewArticlePricePublisherPort articlePricePublisherPort(
            KafkaTemplate<String, NewArticlePricePublisherPortKafka.NewPriceMessage> articlePriceKafkaTemplate) {
        return new NewArticlePricePublisherPortKafka(
                articlePriceKafkaTemplate);
    }

    @Bean
    public NewArticlePublisherPort newArticlePublisherPort(
            KafkaTemplate<String, String> newTopicKafkaTemplate) {
        return new NewArticlePublisherPortKafka(newTopicKafkaTemplate);
    }

}
