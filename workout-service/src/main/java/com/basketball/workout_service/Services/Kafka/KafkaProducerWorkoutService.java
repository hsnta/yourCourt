package com.basketball.workout_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerWorkoutService {
    private static final String TOPIC = "workout_service_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String workoutId) {
        kafkaTemplate.send(TOPIC, workoutId);
    }
}
