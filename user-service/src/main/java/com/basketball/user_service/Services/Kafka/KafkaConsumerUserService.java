package com.basketball.user_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerUserService {

    @Autowired
    MessageProcessorUserService messageProcessorUserService;

    @KafkaListener(topics = "example_topic", groupId = "group_id")
    public void consume(String message) {
        messageProcessorUserService.getExampleWorkoutData(message);

    }
}