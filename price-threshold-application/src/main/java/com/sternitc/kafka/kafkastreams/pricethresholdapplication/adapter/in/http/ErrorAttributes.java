package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.in.http;

public enum ErrorAttributes {

    ERROR_CODE("error_code"),
    STATUS_CODE("http_status_code"),
    URL("url"),
    TIMESTAMP("timestamp"),
    MESSAGE("error_message");

    private String attribute;

    private ErrorAttributes(String value) {
        this.attribute = value;
    }

    public String getAttribute() {
        return attribute;
    }
}
