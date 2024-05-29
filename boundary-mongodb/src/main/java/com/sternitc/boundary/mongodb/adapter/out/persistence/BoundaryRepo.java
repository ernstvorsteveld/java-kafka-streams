package com.sternitc.boundary.mongodb.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BoundaryRepo extends ReactiveMongoRepository<BoundaryDocument, String> {
}
