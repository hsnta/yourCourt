package com.basketball.drill_service.Controllers;

import com.basketball.drill_service.Services.DrillService;
import com.basketball.drill_service.codegen.types.Drill;
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

    @DgsQuery
    public Drill getDrillById(@InputArgument("drillId") String drillId) {
        return drillService.getDrillById(drillId);
    }

    @DgsQuery
    public List<Drill> getAllDrillsByUserId(@InputArgument("userId") String userId) {
        return drillService.getAllDrillsByUserId(userId);
    }

    @DgsQuery
    public List<Drill> getAllDrillsByWorkoutId(@InputArgument("workoutId") String workoutId) {
        return drillService.getAllDrillsByWorkoutId(workoutId);
    }

    @DgsMutation
    public Drill createDrill(@InputArgument("drillInput") Drill drill) {
        return drillService.createDrill(drill);
    }

    @DgsMutation
    public Drill updateDrill(@InputArgument("drillInput") Drill drill) {
        return drillService.updateDrill(drill);
    }

    @DgsMutation
    public Boolean deleteDrill(@InputArgument("drillId") String drillId) {
        return drillService.deleteDrill(drillId);
    }

}
