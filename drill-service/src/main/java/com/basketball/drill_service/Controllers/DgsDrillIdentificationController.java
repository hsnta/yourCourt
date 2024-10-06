package com.basketball.drill_service.Controllers;

import com.basketball.codegen_service.codegen.types.DrillIdentification;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.drill_service.Services.DrillIdentificationService;
import com.basketball.drill_service.Services.Kafka.KafkaProducerDrillService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;


@DgsComponent
public class DgsDrillIdentificationController {

    @Autowired
    DrillIdentificationService drillIdentificationService;

    @Autowired
    KafkaProducerDrillService kafkaProducerDrillService;

    @DgsQuery
    public DrillIdentification getDrillIdentificationByDrillType(@InputArgument("drillType") DrillType drillType) {
        try {
            return drillIdentificationService.getDrillIdentificationByDrillType(drillType);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by type " + drillType);
        }
        return null;
    }

    @DgsMutation
    public DrillIdentification createDrillIdentification(@InputArgument("drillIdentification") DrillIdentification drillIdentification) {
        return drillIdentificationService.createDrillIdentification(drillIdentification);
    }
}
