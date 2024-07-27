package com.basketball.workout_service.Services.Kafka;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.basketball.codegen_service.codegen.types.DrillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KafkaProducerWorkoutService {
    private static final String TOPIC = "workout_service_topic";
    private static final String CREATE_DRILLS_FROM_WORKOUT_SELECTION = "create_drills_from_workout_selection";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, DrillCreationRequest> drillKafkaTemplate;

    public void sendMessage(String workoutId) {
        kafkaTemplate.send(TOPIC, workoutId);
    }

    public void sendDrillsToDrillService(DrillCreationRequest drillCreationRequest) {
        drillKafkaTemplate.send(CREATE_DRILLS_FROM_WORKOUT_SELECTION, drillCreationRequest);
    }
}
