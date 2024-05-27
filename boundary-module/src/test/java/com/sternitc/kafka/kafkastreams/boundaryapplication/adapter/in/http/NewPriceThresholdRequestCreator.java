package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;

import java.util.UUID;

public class NewPriceThresholdRequestCreator {

    public NewPriceThresholdRequest expectNewPriceThreshold() {
        return new NewPriceThresholdRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                "INCREASE",
                10);
    }

}
