package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WorkoutMessageController {

    @Autowired
    private KafkaProducerWorkoutService kafkaProducerWorkoutService;

    public void sendMessage(@RequestBody String workoutId) {
        kafkaProducerWorkoutService.sendMessageToUI(workoutId);
    }
}
