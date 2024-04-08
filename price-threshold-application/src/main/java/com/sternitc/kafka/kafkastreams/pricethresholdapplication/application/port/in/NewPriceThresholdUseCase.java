package com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.NewPriceThresholdCommand;
import reactor.core.publisher.Mono;

public interface NewPriceThresholdUseCase {

    Identity accept(NewPriceThresholdCommand command);

    record Identity(String id) {
    }
}
