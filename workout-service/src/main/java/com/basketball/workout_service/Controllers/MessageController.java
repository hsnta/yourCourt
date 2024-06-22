package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private KafkaProducerWorkoutService kafkaProducerWorkoutService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody String workoutId) {
        kafkaProducerWorkoutService.askForDrillCreation(workoutId);
    }
}
