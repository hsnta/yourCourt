package com.basketball.user_service.Services;

import com.basketball.user_service.Models.DrillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageProcessorUserService {

    @Autowired
    private RestTemplate restTemplate;

    public void getExampleWorkoutData(String message) {
        String serviceAUrl = "http://localhost:8765/api/workout/exampleWorkout";
        DrillModel model = restTemplate.getForObject(serviceAUrl, DrillModel.class);
        System.out.println("Data from Service B: " + model);
    }
}
