package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.generated.api.boundary.model.NewPriceBoundaryRequest;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceBoundaryCommand;

public class Mapper {
    public NewPriceBoundaryCommand to(NewPriceBoundaryRequest request) {
        return new NewPriceBoundaryCommand(
                request.getArticleId().toString(),
                request.getCompanyId().toString(),
                request.getUserId().toString(),
                request.getBoundaryType(),
                request.getBoundary());
    }
}

