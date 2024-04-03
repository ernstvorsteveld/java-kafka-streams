package com.sternitc.kafka.articleprice.application.port.in;

import com.sternitc.kafka.articleprice.ArticlePriceApplication;
import com.sternitc.kafka.articleprice.application.port.in.NewArticlePricesUseCase.NewArticlePrice;
import com.sternitc.kafka.articleprice.application.port.in.NewArticlePricesUseCase.NewArticlePrices;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {ArticlePriceApplication.class}
)
@EmbeddedKafka(partitions = 1,
        topics = {"${topic.new.article.prices.name}"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}
)
class NewArticlePricesUseCaseTest {

    @Autowired
    private NewArticlePricesUseCase newArticlePricesUseCase;

    @Value("${topic.new.article.prices.name}")
    private String newArticlePricesTopic;

    NewArticlePrice house = new NewArticlePrice("house", 1000);
    NewArticlePrice car = new NewArticlePrice("car", 100);
    NewArticlePrice factory = new NewArticlePrice("factory", 1000000);

    @Test
    public void should_publish_new_article_price_messages(@Autowired EmbeddedKafkaBroker embeddedKafkaBroker) {
        newArticlePricesUseCase.newArticlePrices(new NewArticlePrices(List.of(house, car)));

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group_consumer_test", "false", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<String, NewArticlePrice> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new StringDeserializer(), new JsonDeserializer<>(NewArticlePrice.class, false));
        Consumer<String, NewArticlePrice> consumerServiceTest = cf.createConsumer();

        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumerServiceTest, newArticlePricesTopic);
        ConsumerRecords<String, NewArticlePrice> records = KafkaTestUtils.getRecords(consumerServiceTest, Duration.of(10, ChronoUnit.SECONDS), 2);

        assertThat(records.iterator().next().value()).isIn(house, car).isNotIn(factory);
        assertThat(records.iterator().next().value()).isIn(house, car).isNotIn(factory);
    }

}