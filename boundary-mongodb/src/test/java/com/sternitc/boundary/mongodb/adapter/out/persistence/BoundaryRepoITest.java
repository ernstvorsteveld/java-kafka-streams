package com.sternitc.boundary.mongodb.adapter.out.persistence;

import com.sternitc.boundary.mongodb.MongoDBTestContainerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
@ContextConfiguration(classes = MongoDBTestContainerConfig.class)
class BoundaryRepoITest {

    @Autowired
    BoundaryRepo boundaryRepo;

    @BeforeEach
    public void cleanUp() {
        boundaryRepo.deleteAll();
    }

    @Test
    void should_create_boundary() {
        BoundaryDocument boundary = new BoundaryDocument(
                UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(),
                "INCREASE", 10.8D);
        Mono<BoundaryDocument> document = boundaryRepo.save(boundary);
        StepVerifier.create(document)
                .assertNext(d -> {
                    assertThat(d.id()).isNotBlank();
                    assertThat(d.boundaryValue()).isEqualTo(10.80);
                })
                .verifyComplete();

        StepVerifier.create(boundaryRepo.findAll())
                .assertNext(o -> System.out.println(o))
                .verifyComplete();

        StepVerifier
                .create(boundaryRepo.count())
                .expectNext(1L)
                .expectComplete()
                .verify();

        Flux<BoundaryDocument> boundaryDocument = boundaryRepo.findAll()
                .doOnNext(b -> boundaryRepo.findById(b.id()));

        StepVerifier.create(boundaryDocument)
                .assertNext(b -> assertThat(b.boundaryValue()).isEqualTo(10.80))
                .expectComplete()
                .verify();
    }

}