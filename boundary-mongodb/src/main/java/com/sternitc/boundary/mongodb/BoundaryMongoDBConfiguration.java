package com.sternitc.boundary.mongodb;

import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryRepo;
import com.sternitc.boundary.mongodb.application.domain.service.BoundaryDaoMongo;
import com.sternitc.boundary.mongodb.application.domain.service.BoundaryMapper;
import com.sternitc.boundary.mongodb.application.domain.service.BoundaryMapperImpl;
import com.sternitc.boundary.mongodb.application.port.BoundaryDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoundaryMongoDBConfiguration {

    @Bean
    public BoundaryDao boundaryDao(
            BoundaryRepo boundaryRepo,
            BoundaryMapper boundaryMapper) {
        return new BoundaryDaoMongo(boundaryRepo, boundaryMapper);
    }
}
