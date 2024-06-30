package com.basketball.drill_service.Controllers;

import com.basketball.drill_service.Services.Kafka.KafkaProducerDrillService;

import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/drill/message")
public class DgsDrillMessageController {

    @Autowired
    private KafkaProducerDrillService kafkaProducerDrillService;

    @GetMapping("/{message}")
    public String sendMessage(@PathVariable String message) {
        return kafkaProducerDrillService.sendMessage(message);
    }
}
