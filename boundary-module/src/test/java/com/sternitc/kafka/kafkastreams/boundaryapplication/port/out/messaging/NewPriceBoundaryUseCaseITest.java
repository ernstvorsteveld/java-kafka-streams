package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.BoundaryApplication;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceThresholdCommand;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.GetArticlePriceThreshold;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {BoundaryApplication.class}
)
@ActiveProfiles({"no-mongodb", "kafka"})
class NewPriceThresholdUseCaseITest {

    @Autowired
    private NewPriceThresholdUseCase newPriceThresholdUseCase;

    @Autowired
    private GetArticlePriceThreshold getArticlePriceThreshold;

    @Test
    public void should_store_and_publish_new_article_price_threshold() throws InterruptedException {
        NewPriceThresholdCommand command = new NewPriceThresholdCommand("10", "11", "12", "INCREASE", 10);
        newPriceThresholdUseCase.accept(command);

        assertThat(getArticlePriceThreshold.get("10").value()).isEqualTo(10);
        TimeUnit.SECONDS.sleep(1);
    }
}