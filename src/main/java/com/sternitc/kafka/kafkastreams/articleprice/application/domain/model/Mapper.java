package com.sternitc.kafka.kafkastreams.articleprice.application.domain.model;

public interface Mapper<F, T> {

    T to(F in);

    F from(T in);
}
