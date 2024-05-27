package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(
        properties = "spring.main.web-application-type=reactive",
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class PriceThresholdsApiITest {

    @LocalServerPort
    private int port;

    private final NewPriceThresholdRequestCreator creator = new NewPriceThresholdRequestCreator();

    @Test
    public void should_create_threshold() {
        NewPriceThresholdRequest body = creator.expectNewPriceThreshold();
        RestAssured.baseURI = "http://localhost:" + port;
        given()
                .when()
                .request()
                .body(body)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .post("/price-thresholds")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

}
