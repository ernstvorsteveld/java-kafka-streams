package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

public class IsNewArticlePriceTopic extends Exception {

    private final String name;

    public IsNewArticlePriceTopic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
