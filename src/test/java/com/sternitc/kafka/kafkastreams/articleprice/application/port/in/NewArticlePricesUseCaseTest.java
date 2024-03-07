package com.sternitc.kafka.kafkastreams.articleprice.application.port.in;

import com.sternitc.kafka.kafkastreams.KafkaStreamsApplication;
import com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging.ArticleTopicsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBootApplication(
        scanBasePackageClasses = KafkaStreamsApplication.class,
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class
        })
@ActiveProfiles("no-mongodb")
class NewArticlePricesUseCaseTest {

    @Autowired
    private NewArticlePricesUseCase useCase;

    @Autowired
    private ArticleTopicsService articleTopicsService;

    @Test
    public void should_send_new_topic_name_for_new_article() {
        Collection<NewArticlePricesUseCase.NewArticlePrice> newPrices = newArticlePrices();
        NewArticlePricesUseCase.NewArticlePrices newArticlePrices = new NewArticlePricesUseCase.NewArticlePrices(newPrices);
        int count = useCase.newArticlePrices(newArticlePrices);
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void should_send_prices_for_existing_topics() {
        Collection<NewArticlePricesUseCase.NewArticlePrice> newPrices = newArticlePrices();
        NewArticlePricesUseCase.NewArticlePrices newArticlePrices = new NewArticlePricesUseCase.NewArticlePrices(newPrices);
        int count = useCase.newArticlePrices(newArticlePrices);
        assertThat(count).isEqualTo(1);
    }

    private Collection<NewArticlePricesUseCase.NewArticlePrice> newArticlePrices() {
        List<NewArticlePricesUseCase.NewArticlePrice> prices = new ArrayList<>();
        prices.add(new NewArticlePricesUseCase.NewArticlePrice("T1", 100));
        return prices;
    }

}