package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.port.in.NewPriceThresholdUseCase;
import com.sternitc.pricethreshold.api.PriceThresholdsApi;
import com.sternitc.pricethreshold.api.model.NewPriceThresholdRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class PriceThresholdsController implements PriceThresholdsApi {

    private NewPriceThresholdUseCase newPriceThresholdUseCase;

    private final LocationCreator locationCreator;
    private final Mapper mapper;

    public PriceThresholdsController(
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

}
