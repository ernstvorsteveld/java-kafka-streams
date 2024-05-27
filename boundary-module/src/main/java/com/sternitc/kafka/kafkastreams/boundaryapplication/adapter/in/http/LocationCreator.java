package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceBoundaryUseCase;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class LocationCreator {

    private final String location;

    public LocationCreator(String location) {
        this.location = location;
    }

    public ResponseEntity<Void> from(NewPriceBoundaryUseCase.Identity identity) {
        return ResponseEntity.created(URI.create(String.format(location, identity.id()))).build();
    }

}
