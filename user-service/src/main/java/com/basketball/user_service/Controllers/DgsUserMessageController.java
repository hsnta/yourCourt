package com.basketball.user_service.Controllers;


import com.basketball.user_service.Services.Kafka.KafkaProducerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DgsUserMessageController {

    @Autowired
    private KafkaProducerUserService kafkaProducerUserService;

    public void sendMessage(@RequestBody String workoutId) {
        kafkaProducerUserService.sendMessage(workoutId);
    }
}
