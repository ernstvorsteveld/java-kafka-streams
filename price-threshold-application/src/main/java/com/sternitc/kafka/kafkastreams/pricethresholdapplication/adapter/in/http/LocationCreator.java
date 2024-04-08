package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class LocationCreator {

    private final String location;

    public LocationCreator(String location) {
        this.location = location;
    }

    public ResponseEntity<Void> from(NewPriceThresholdUseCase.Identity identity) {
        return ResponseEntity.created(URI.create(String.format(location, identity.id()))).build();
    }

}
