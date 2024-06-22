package com.basketball.user_performance.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerUserPerformanceService {

    @Autowired
    MessageProcessorUserPerformanceService messageProcessorUserPerformanceService;

    @KafkaListener(topics = "drill_service_topic", groupId = "group_id")
    public void consumeDrillService(String message) {
        messageProcessorUserPerformanceService.getExampleWorkoutData(message);
    }

    @KafkaListener(topics = "user_service_topic", groupId = "group_id")
    public void consumeUserService(String message) {
        messageProcessorUserPerformanceService.getExampleWorkoutData(message);
    }

    @KafkaListener(topics = "user_performance_service_topic", groupId = "group_id")
    public void consumeUserPerformanceService(String message) {
        messageProcessorUserPerformanceService.getExampleWorkoutData(message);
    }
}