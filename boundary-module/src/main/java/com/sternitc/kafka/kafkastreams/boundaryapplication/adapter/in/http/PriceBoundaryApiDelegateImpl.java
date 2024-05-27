package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.in.http;

import com.sternitc.generated.api.boundary.PriceBoundaryApplicationApi;
import com.sternitc.generated.api.boundary.model.NewPriceBoundaryRequest;
import com.sternitc.kafka.kafkastreams.boundaryapplication.application.port.in.NewPriceThresholdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class PriceThresholdsApiDelegateImpl implements PriceBoundaryApplicationApi {

    private NewPriceThresholdUseCase newPriceThresholdUseCase;
    private final LocationCreator locationCreator;
    private final Mapper mapper;

    @Autowired
    public PriceThresholdsApiDelegateImpl(
            NewPriceThresholdUseCase newPriceThresholdUseCase,
            LocationCreator locationCreator,
            Mapper mapper) {
        this.newPriceThresholdUseCase = newPriceThresholdUseCase;
        this.locationCreator = locationCreator;
        this.mapper = mapper;
    }

    @Override
    public Mono<ResponseEntity<Void>> createPriceBoundary(
            Mono<NewPriceBoundaryRequest> newPriceThresholdRequest,
            final ServerWebExchange exchange) {
        return newPriceThresholdRequest
                .map(mapper::to)
                .map(newPriceThresholdUseCase::accept)
                .map(locationCreator::from);
    }

    @Override
    public Mono<ResponseEntity<String>> getPriceBoundaries(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok("Duttet"));
    }
}
