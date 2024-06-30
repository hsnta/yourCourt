package com.basketball.workout_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerWorkoutService {

    @Autowired
    MessageProcessorWorkoutService messageProcessorWorkoutService;

    @KafkaListener(topics = "drill_service_topic", groupId = "group_id")
    public void consumeDrillService(String message) {
        messageProcessorWorkoutService.getMessage(message);
    }

    @KafkaListener(topics = "user_service_topic", groupId = "group_id")
    public void consumeUserService(String message) {
        messageProcessorWorkoutService.getMessage(message);
    }

    @KafkaListener(topics = "user_performance_service_topic", groupId = "group_id")
    public void consumeUserPerformanceService(String message) {
        messageProcessorWorkoutService.getMessage(message);
    }
}