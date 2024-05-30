package com.sternitc.boundary.mongodb.application.domain.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoundaryApplicationServiceConfiguration {

    @Bean
    public BoundaryMapper boundaryMapper() {
        return new BoundaryMapperImpl();
    }

}
