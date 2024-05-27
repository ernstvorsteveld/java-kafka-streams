package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging.NewPriceBoundaryPublisherPortKafka.NewArticlePriceBoundaryMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class NewArticlePriceBoundaryMessageSerializer implements Serializer<NewArticlePriceBoundaryMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, NewArticlePriceBoundaryMessage data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
