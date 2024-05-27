package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceThresholdCommand;

public interface NewPriceThresholdUseCase {

    Identity accept(NewPriceThresholdCommand command);

    record Identity(String id) {
    }
}
