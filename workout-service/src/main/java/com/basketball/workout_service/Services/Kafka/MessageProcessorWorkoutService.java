package com.basketball.workout_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageProcessorWorkoutService {

    @Autowired
    private RestTemplate restTemplate;

    public void getExampleWorkoutData(String message) {
        String serviceAUrl = "http://localhost:8765/api/workout/exampleWorkout";
        System.out.println("Data from Service B: ");
    }
}
