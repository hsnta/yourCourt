package com.basketball.drill_service.Services.Kafka;

import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.basketball.codegen_service.codegen.types.DrillModel;
import com.basketball.drill_service.Services.DrillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerDrillService {

    @Autowired
    MessageProcessorDrillService messageProcessorDrillService;
    @Autowired
    DrillService drillService;


    @KafkaListener(topics = "create_drills_from_workout_selection", groupId = "group_id")
    public List<Drill> consumeWorkoutServiceDrillCreation(String drillCreationRequestJsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            DrillCreationRequest drillCreationRequest = objectMapper.readValue(drillCreationRequestJsonString, DrillCreationRequest.class);
            return drillService.createDrillFromDrillCreationRequest(drillCreationRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "user_service_topic", groupId = "group_id")
    public void consumeUserService(String message) {
        messageProcessorDrillService.getExampleWorkoutData(message);
    }

    @KafkaListener(topics = "user_performance_service_topic", groupId = "group_id")
    public void consumeUserPerformanceService(String message) {
        messageProcessorDrillService.getExampleWorkoutData(message);
    }
}