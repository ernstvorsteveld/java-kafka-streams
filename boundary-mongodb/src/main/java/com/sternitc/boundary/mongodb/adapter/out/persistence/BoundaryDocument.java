package com.sternitc.boundary.mongodb.adapter.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boundaries")
public record BoundaryDocument(
        @Id String id,
        String companyId,
        String userId,
        String articleId,
        String boundaryType,
        double boundaryValue) {

    @PersistenceCreator
    public BoundaryDocument {
    }

    public BoundaryDocument(String companyId,
                            String userId,
                            String articleId,
                            String boundaryType,
                            double boundaryValue) {
        this(null, companyId, userId, articleId, boundaryType, boundaryValue);
    }

}
