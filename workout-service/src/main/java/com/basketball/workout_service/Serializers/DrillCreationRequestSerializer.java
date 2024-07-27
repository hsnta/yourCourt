package com.basketball.workout_service.Serializers;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class DrillCreationRequestSerializer implements Serializer<DrillCreationRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, DrillCreationRequest data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing value", e);
        }
    }

    @Override
    public void close() {
    }
}