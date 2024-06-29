package com.basketball.user_performance.Controllers;

import com.basketball.user_performance.Services.Kafka.KafkaProducerUserPerformanceService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DgsUserPerformanceMessageController {

    @Autowired
    private KafkaProducerUserPerformanceService kafkaProducerUserPerformanceService;


    public void sendMessage(@RequestBody String workoutId) {
        kafkaProducerUserPerformanceService.sendMessage(workoutId);
    }
}
