package com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.kafka.kafkastreams.pricethresholdapplication.adapter.out.messaging.NewPriceThresholdPublisherPortKafka.NewArticlePriceThresholdMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class NewArticlePriceThresholdMessageDeserializer implements Deserializer<NewArticlePriceThresholdMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NewArticlePriceThresholdMessage deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NewArticlePriceThresholdMessage.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
