package com.sternitc.kafka.articleprice.adapter.out.messaging;

import com.sternitc.kafka.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import static com.sternitc.kafka.articleprice.adapter.out.messaging.NewArticlePricePublisherPortKafka.NewArticlePriceEvent;

@Configuration
public class MessagingConfiguration {

    @Value("${topic.new.article.prices.name}")
    public String newArticlePriceTopic;

    @Value("${topic.new.article.prices.partions}")
    public int newArticlePriceTopicPartitions;

    @Bean
    public NewTopic newArticlePriceTopic() {
        return TopicBuilder.name(newArticlePriceTopic)
                .partitions(newArticlePriceTopicPartitions)
                .replicas(1)
                .build();
    }

    @Bean
    public NewArticlePricePublisherPort articlePricePublisherPort(
            KafkaTemplate<String, NewArticlePriceEvent> articlePriceKafkaTemplate) {
        return new NewArticlePricePublisherPortKafka(
                newArticlePriceTopic,
                articlePriceKafkaTemplate);
    }

}
