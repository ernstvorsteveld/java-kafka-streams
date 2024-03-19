package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.PriceThresholdApplication;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.NewPriceThresholdCommand;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.out.persistence.GetArticlePriceThreshold;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBootApplication(
        scanBasePackageClasses = PriceThresholdApplication.class,
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class
        })
@ActiveProfiles({"no-mongodb", "kafka"})
class NewPriceThresholdUseCaseITest {

    @Autowired
    private NewPriceThresholdUseCase newPriceThresholdUseCase;

    @Autowired
    private GetArticlePriceThreshold getArticlePriceThreshold;

    @Autowired
    private Map<String, Map<String, Object>> eventMap;

    @Test
    public void should_store_and_publish_new_article_price_threshold() throws InterruptedException {
        NewPriceThresholdCommand command = new NewPriceThresholdCommand("10", "UPPER", 10);
        newPriceThresholdUseCase.accept(command);

        assertThat(getArticlePriceThreshold.get("10").value()).isEqualTo(10);
        TimeUnit.SECONDS.sleep(1);
        assertThat(eventMap.size()).isEqualTo(1);
    }
}