package com.basketball.drill_service.Controllers;

import com.basketball.drill_service.Services.Kafka.KafkaProducerDrillService;

import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DgsDrillMessageController {

    @Autowired
    private KafkaProducerDrillService kafkaProducerDrillService;


    public String sendMessage(@InputArgument("message") String message) {
        return kafkaProducerDrillService.sendMessage(message);
    }
}
