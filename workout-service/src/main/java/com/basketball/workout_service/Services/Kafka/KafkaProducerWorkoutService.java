package com.basketball.workout_service.Services.Kafka;

import com.basketball.workout_service.codegen.types.DrillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerWorkoutService {
    private static final String TOPIC = "workout_service_topic";
    private static final String CREATE_DRILLS = "create_drills";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, List<DrillModel>> drillTemplate;

    public void sendMessage(String workoutId) {
        kafkaTemplate.send(TOPIC, workoutId);
    }

    public void sendDrillsToDrillService(List<DrillModel> drills) {
        drillTemplate.send(CREATE_DRILLS, drills);
    }
}
