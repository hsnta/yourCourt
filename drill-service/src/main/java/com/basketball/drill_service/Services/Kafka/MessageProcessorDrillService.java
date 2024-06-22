package com.basketball.drill_service.Services.Kafka;

import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import com.basketball.drill_service.Resolvers.DrillMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageProcessorDrillService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducerDrillService kafkaProducerDrillService;

    private DrillMutationResolver drillMutationResolver;

    public void getExampleWorkoutData(String message) {
        String serviceAUrl = "http://localhost:8765/api/workout/exampleWorkout";
        System.out.println("Data from Service B: ");
    }

    public DrillEntity createDrill(DrillModel drillModel) {
        DrillEntity drill = drillMutationResolver.createDrill(drillModel);
        kafkaProducerDrillService.sendNewDrill(drill);
        return drill;
    }
}
