package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.UUID;

//@WebFluxTest(controllers = {PriceThresholdsApiController.class})
@WebFluxTest
@Import(PriceThresholdsApiDelegateImplTestConfiguration.class)
class PriceThresholdsApiDelegateImplTest {

    @Autowired
    private WebTestClient webTestClient;

    private final NewPriceThresholdRequestCreator creator = new NewPriceThresholdRequestCreator();

    @Test
    public void should_create_threshold() throws Exception {
        NewPriceThresholdRequest body = creator.expectNewPriceThreshold();
        webTestClient
                .post()
                .uri("/v1/price-thresholds")
                .body(BodyInserters.fromValue(body))
                .exchange()
                .expectStatus().isCreated();
    }


}