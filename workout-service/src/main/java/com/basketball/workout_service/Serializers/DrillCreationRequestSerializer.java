package com.basketball.workout_service.Serializers;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class DrillCreationRequestSerializer implements Serializer<DrillCreationRequest> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, DrillCreationRequest data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing DrillCreationRequest", e);
        }
    }
}