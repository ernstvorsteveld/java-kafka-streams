package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.BoundaryApplication;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceBoundaryCommand;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceBoundaryUseCase;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.out.persistence.GetArticlePriceBoundary;
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
class NewPriceBoundaryUseCaseITest {

    @Autowired
    private NewPriceBoundaryUseCase newPriceBoundaryUseCase;

    @Autowired
    private GetArticlePriceBoundary getArticlePriceBoundary;

    @Test
    public void should_store_and_publish_new_article_price_threshold() throws InterruptedException {
        NewPriceBoundaryCommand command = new NewPriceBoundaryCommand("10", "11", "12", "INCREASE", 10);
        newPriceBoundaryUseCase.accept(command);

        assertThat(getArticlePriceBoundary.get("10").value()).isEqualTo(10);
        TimeUnit.SECONDS.sleep(1);
    }
}