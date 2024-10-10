package com.basketball.workout_service.Services.Kafka;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerWorkoutService {
    private static final String TOPIC = "workout_service_topic";
    private static final String UI_COMMUNICATION = "ui_communication_topic";
    private static final String CREATE_DRILLS_FROM_CUSTOM_WORKOUT = "create_drills_from_workout_selection";

    @Autowired
    private KafkaTemplate<String, String> messageTemplate;

    @Autowired
    private KafkaTemplate<String, DrillCreationRequest> drillKafkaTemplate;

    public void sendMessageToUI(String workoutId) {
        messageTemplate.send(UI_COMMUNICATION, workoutId);
    }

    public void sendDrillsToDrillService(DrillCreationRequest drillCreationRequest) {
        drillKafkaTemplate.send(CREATE_DRILLS_FROM_CUSTOM_WORKOUT, drillCreationRequest);
    }
}
