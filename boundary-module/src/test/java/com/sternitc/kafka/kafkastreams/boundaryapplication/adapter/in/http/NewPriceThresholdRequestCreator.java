package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.generated.api.boundary.model.NewPriceBoundaryRequest;

import java.util.UUID;

public class NewPriceThresholdRequestCreator {

    public NewPriceBoundaryRequest expectNewPriceBoundary() {
        return new NewPriceBoundaryRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "INCREASE",
                10);
    }

}
