package com.basketball.user_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageProcessorUserService {

    @Autowired
    private RestTemplate restTemplate;

    public void getMessage(String message) {
        System.out.println("Data from Service B: " + message);
    }
}
