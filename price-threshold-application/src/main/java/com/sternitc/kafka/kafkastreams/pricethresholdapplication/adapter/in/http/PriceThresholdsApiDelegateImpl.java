package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.pricethreshold.api.PriceThresholdApi;
import com.sternitc.pricethreshold.api.PriceThresholdsApplicationApi;
import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class PriceThresholdsApiDelegateImpl implements PriceThresholdApi, PriceThresholdsApplicationApi {

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
    public Mono<ResponseEntity<Void>> createPriceThreshold(
            Mono<NewPriceThresholdRequest> newPriceThresholdRequest,
            final ServerWebExchange exchange) {
        return newPriceThresholdRequest
                .map(mapper::to)
                .map(newPriceThresholdUseCase::accept)
                .map(locationCreator::from);
    }

    @Override
    public Mono<ResponseEntity<String>> getPriceThresholds(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok("Duttet"));
    }
}
