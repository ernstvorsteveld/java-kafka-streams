package com.sternitc.boundary.mongodb.application.port;

import com.sternitc.boundary.mongodb.BoundaryMongoDBConfiguration;
import com.sternitc.boundary.mongodb.MongoDBTestContainerConfig;
import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryDocument;
import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryRepo;
import com.sternitc.boundary.mongodb.application.domain.service.BoundaryApplicationServiceConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Testcontainers
@ContextConfiguration(
        classes = {
                BoundaryApplicationServiceConfiguration.class,
                MongoDBTestContainerConfig.class,
                BoundaryMongoDBConfiguration.class
        })
class BoundaryDaoTest {

    @Autowired
    private BoundaryDao boundaryDao;

    @Autowired
    private BoundaryRepo boundaryRepo;

    @BeforeEach
    public void cleanUp() {
        boundaryRepo.deleteAll();
    }

    @Test
    public void should_save_boundary() {
        Mono<BoundaryDao.BoundaryDto> boundary = boundaryDao.save(
                Mono.just(
                        new BoundaryDao.BoundaryDto(
                                new BoundaryDao.CompanyId("10"),
                                new BoundaryDao.UserId("100"),
                                new BoundaryDao.ArticleId("2000"),
                                new BoundaryDao.BoundaryType("INCREASE"),
                                new BoundaryDao.BoundaryValue(20.20)
                        )));

        StepVerifier.create(boundary)
                .assertNext(dto -> {
                    assertThat(dto.id()).isNotBlank();
                    assertThat(dto.companyId().id()).isEqualTo("10");
                    assertThat(dto.userId().id()).isEqualTo("100");
                    assertThat(dto.articleId().id()).isEqualTo("2000");
                    assertThat(dto.boundaryType().type()).isEqualTo("INCREASE");
                    assertThat(dto.boundaryValue().value()).isEqualTo(20.20);
                })
                .expectComplete()
                .verify();

        StepVerifier.create(boundaryRepo.findAll())
                .assertNext(d -> assertThat(d.id()).isNotBlank())
                .expectComplete()
                .verify();

        StepVerifier
                .create(boundaryRepo.count())
                .expectNext(1L)
                .expectComplete()
                .verify();

    }

}