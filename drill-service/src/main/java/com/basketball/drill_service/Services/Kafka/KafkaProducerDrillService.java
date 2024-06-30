package com.basketball.drill_service.Services.Kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerDrillService {
    private static final String TOPIC = "drill_service_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message to " + TOPIC + " " + message + " successfully sent");
        return message;
    }
}
