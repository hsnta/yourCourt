package com.basketball.video_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageProcessorVideoService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducerVideoService kafkaProducerVideoService;

    public void getExampleWorkoutData(String message) {
        System.out.println("Data from Service B: " + message);
    }
}
