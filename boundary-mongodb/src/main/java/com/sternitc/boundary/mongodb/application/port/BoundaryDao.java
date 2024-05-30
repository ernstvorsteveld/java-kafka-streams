package com.sternitc.boundary.mongodb.application.port;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoundaryDao {

    Mono<BoundaryDto> save(Mono<BoundaryDto> boundaryDto);

    Mono<BoundaryDto> findById(String id);

    Flux<Page<BoundaryDto>> findByCompanyId(CompanyId id, Pageable pageable);

    Flux<Page<BoundaryDto>> findByUserId(UserId id, Pageable pageable);

    Flux<Page<BoundaryDto>> findByArticleId(ArticleId id, Pageable pageable);

    record CompanyId(String id) {
    }

    record UserId(String id) {
    }

    record ArticleId(String id) {
    }

    record BoundaryType(String type) {
    }

    record BoundaryValue(double value) {
    }

    record BoundaryDto(String id, CompanyId companyId, UserId userId, ArticleId articleId,
                       BoundaryType boundaryType, BoundaryValue boundaryValue) {
        public BoundaryDto(CompanyId companyId, UserId userId, ArticleId articleId,
                           BoundaryType boundaryType, BoundaryValue boundaryValue) {
            this(null, companyId, userId, articleId, boundaryType, boundaryValue);
        }
    }
}
