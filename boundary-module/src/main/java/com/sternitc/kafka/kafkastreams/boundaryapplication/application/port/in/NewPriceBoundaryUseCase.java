package com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.domain.model.NewPriceBoundaryCommand;

public interface NewPriceBoundaryUseCase {

    Identity accept(NewPriceBoundaryCommand command);

    record Identity(String id) {
    }
}
