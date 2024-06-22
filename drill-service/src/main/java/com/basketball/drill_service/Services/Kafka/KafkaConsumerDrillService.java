package com.basketball.drill_service.Services.Kafka;

import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDrillService {

    @Autowired
    MessageProcessorDrillService messageProcessorDrillService;

    @KafkaListener(topics = "workout_service_topic", groupId = "group_id")
    public DrillEntity consumeWorkoutServiceDrillCreation(DrillModel drillModel) {
        return messageProcessorDrillService.createDrill(drillModel);
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