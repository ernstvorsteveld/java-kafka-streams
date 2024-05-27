package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.NewPriceThresholdCommand;
import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;

public class Mapper {
    public NewPriceThresholdCommand to(NewPriceThresholdRequest request) {
        return new NewPriceThresholdCommand(
                request.getArticleId().toString(),
                request.getCompanyId().toString(),
                request.getUserId().toString(),
                request.getBoundaryType(),
                request.getBoundary());
    }
}

