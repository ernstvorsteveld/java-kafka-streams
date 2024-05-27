package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.generated.api.boundary.PriceBoundaryApplicationApi;
import com.sternitc.generated.api.boundary.model.NewPriceBoundaryRequest;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceBoundaryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class PriceBoundaryApiDelegateImpl implements PriceBoundaryApplicationApi {

    private final NewPriceBoundaryUseCase newPriceBoundaryUseCase;
    private final LocationCreator locationCreator;
    private final Mapper mapper;

    @Autowired
    public PriceBoundaryApiDelegateImpl(
            NewPriceBoundaryUseCase newPriceBoundaryUseCase,
            LocationCreator locationCreator,
            Mapper mapper) {
        this.newPriceBoundaryUseCase = newPriceBoundaryUseCase;
        this.locationCreator = locationCreator;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResponseEntity<Void>> createPriceBoundary(
            Mono<NewPriceBoundaryRequest> newPriceBoundaryRequest,
            final ServerWebExchange exchange) {
        return newPriceBoundaryRequest
                .map(mapper::to)
                .map(newPriceBoundaryUseCase::accept)
                .map(locationCreator::from);
    }

    @Override
    public Mono<ResponseEntity<String>> getPriceBoundaries(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok("Duttet"));
    }
}
