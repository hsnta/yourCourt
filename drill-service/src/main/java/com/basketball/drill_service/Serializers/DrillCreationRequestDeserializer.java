package com.basketball.drill_service.Serializers;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DrillCreationRequestDeserializer implements Deserializer<DrillCreationRequest> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public DrillCreationRequest deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, DrillCreationRequest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
    }
}