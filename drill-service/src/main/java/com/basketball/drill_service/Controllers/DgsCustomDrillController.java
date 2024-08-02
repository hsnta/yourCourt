package com.basketball.drill_service.Controllers;

import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.drill_service.Services.DrillService;
import com.basketball.drill_service.Services.Kafka.KafkaProducerDrillService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class DgsCustomDrillController {

    @Autowired
    DrillService drillService;

    @Autowired
    KafkaProducerDrillService kafkaProducerDrillService;

//    @DgsQuery
//    public Drill getCustomDrillByDrillType(@InputArgument("drillId") String drillId) {
//        try {
//            return drillService.getDrillById(drillId);
//        } catch (Exception e) {
//            kafkaProducerDrillService.sendMessage("Unable to get drill by Id " + drillId);
//        }
//        return null;
//    }
//
//    @DgsMutation
//    public Drill createCustomDrill(@InputArgument("drillInput") Drill drill) {
//        return drillService.createDrill(drill);
//    }
}
