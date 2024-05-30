package com.sternitc.boundary.mongodb.application.domain.service;

import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryRepo;
import com.sternitc.boundary.mongodb.application.port.BoundaryDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BoundaryDaoMongo implements BoundaryDao {

    private final BoundaryRepo boundaryRepo;
    private final BoundaryMapper boundaryMapper;

    public BoundaryDaoMongo(
            BoundaryRepo boundaryRepo,
            BoundaryMapper boundaryMapper) {
        this.boundaryRepo = boundaryRepo;
        this.boundaryMapper = boundaryMapper;
    }

    @Override
    public Mono<BoundaryDto> save(Mono<BoundaryDto> boundaryDto) {
        return boundaryDto
                .map(boundaryMapper::toDocument)
                .flatMap(boundaryRepo::save)
                .map(boundaryMapper::toDto);
    }

    @Override
    public Mono<BoundaryDto> findById(String id) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Override
    public Flux<Page<BoundaryDto>> findByCompanyId(CompanyId id, Pageable pageable) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Override
    public Flux<Page<BoundaryDto>> findByUserId(UserId id, Pageable pageable) {
        throw new RuntimeException("Not implemented yet.");
    }

    @Override
    public Flux<Page<BoundaryDto>> findByArticleId(ArticleId id, Pageable pageable) {
        throw new RuntimeException("Not implemented yet.");
    }
}
