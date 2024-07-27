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
public class DgsDrillController {

    @Autowired
    DrillService drillService;

    @Autowired
    KafkaProducerDrillService kafkaProducerDrillService;

    @DgsQuery
    public Drill getDrillById(@InputArgument("drillId") String drillId) {
        try {
            return drillService.getDrillById(drillId);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by Id " + drillId);
        }
        return null;
    }

    @DgsQuery
    public List<Drill> getAllDrillsByUserId(@InputArgument("userId") String userId) {
        try {
            return drillService.getAllDrillsByUserId(userId);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by user Id " + userId);
        }
        return null;
    }

    @DgsQuery
    public List<Drill> getAllDrillsByWorkoutId(@InputArgument("workoutId") String workoutId) {
        try {
            return drillService.getAllDrillsByWorkoutId(workoutId);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by workout Id " + workoutId);
        }
        return null;
    }

    @DgsMutation
    public Drill createDrill(@InputArgument("drillInput") Drill drill) {
        return drillService.createDrill(drill);
    }

    @DgsMutation
    public Drill updateDrill(@InputArgument("drillInput") Drill drill) {
        try {
            return drillService.updateDrill(drill);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by Id " + drill.getDrillId());
        }
        return null;
    }

    @DgsMutation
    public Boolean deleteDrill(@InputArgument("drillId") String drillId) {
        try {
            return drillService.deleteDrill(drillId);
        } catch (Exception e) {
            kafkaProducerDrillService.sendMessage("Unable to get drill by Id " + drillId);
        }
        return null;
    }

}
