package com.sternitc.kafka.kafkastreams.articleapplication.application.domain.service;

public interface Mapper<F, T> {

    T to(F in);

    F from(T in);
}
