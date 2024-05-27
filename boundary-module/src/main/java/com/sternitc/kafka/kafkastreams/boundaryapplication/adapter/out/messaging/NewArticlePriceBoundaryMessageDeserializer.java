package com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sternitc.kafka.kafkastreams.boundaryapplication.adapter.out.messaging.NewPriceBoundaryPublisherPortKafka.NewArticlePriceBoundaryMessage;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class NewArticlePriceBoundaryMessageDeserializer implements Deserializer<NewArticlePriceBoundaryMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NewArticlePriceBoundaryMessage deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NewArticlePriceBoundaryMessage.class);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}
