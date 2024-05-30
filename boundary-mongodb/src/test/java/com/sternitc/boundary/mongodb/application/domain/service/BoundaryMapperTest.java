package com.sternitc.boundary.mongodb.application.domain.service;

import com.sternitc.boundary.mongodb.TestApplication;
import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryDocument;
import com.sternitc.boundary.mongodb.application.port.BoundaryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BoundaryApplicationServiceConfiguration.class)
class BoundaryMapperTest {

    @Autowired
    private BoundaryMapper mapper;

    @Test
    public void should_map_to_dto() {
        BoundaryDao.BoundaryDto dto = mapper.toDto(
                new BoundaryDocument("0","1", "2", "3", "INCREASE", 10.10));
        assertThat(dto.userId().id()).isEqualTo("2");
        assertThat(dto.id()).isEqualTo("0");
    }
}