package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.NewPriceThresholdCommand;

public interface NewPriceThresholdUseCase {

    Identity accept(NewPriceThresholdCommand command);

    record Identity(String id) {
    }
}
