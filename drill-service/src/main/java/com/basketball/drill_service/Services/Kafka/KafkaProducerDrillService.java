package com.basketball.drill_service.Services.Kafka;

import com.basketball.drill_service.Models.DrillEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerDrillService {
    private static final String TOPIC = "drill_service_topic";

    @Autowired
    private KafkaTemplate<String, DrillEntity> kafkaTemplate;

    public void sendNewDrill(DrillEntity drillEntity) {
        kafkaTemplate.send(TOPIC, drillEntity);
    }
}
