package com.basketball.auth_service.service.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerUserService {

    @Autowired
    MessageProcessorUserService messageProcessorUserService;

    @KafkaListener(topics = "user_service_topic", groupId = "group_id")
    public void consume(String message) {
        messageProcessorUserService.getMessage(message);

    }

    @KafkaListener(topics = "user_auth_topic", groupId = "group_id")
    public void consumeUserDetails(String message) {
        messageProcessorUserService.getMessage(message);

    }
}