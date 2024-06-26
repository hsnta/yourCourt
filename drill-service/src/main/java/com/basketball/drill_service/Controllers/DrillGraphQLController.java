package com.basketball.drill_service.Controllers;


import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillInput;
import com.basketball.drill_service.Models.DrillModel;
import com.basketball.drill_service.Services.DrillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DrillGraphQLController {

    @Autowired
    private DrillService drillService;

    @QueryMapping
    public DrillEntity getDrillById(@Argument String drillId) {
        return drillService.getDrillById(drillId);
    }

    @QueryMapping
    public List<DrillEntity> getAllDrillsByWorkoutId(@Argument String workoutId) {
        return drillService.getAllDrillsByWorkoutId(workoutId);
    }

    @QueryMapping
    public List<DrillEntity> getAllDrillsByUserId(@Argument String userId) {
        return drillService.getAllDrillsByUserId(userId);
    }

    @MutationMapping
    public DrillEntity createDrill(@Argument DrillInput drillInput) {
        return drillService.createDrill(drillInput);
    }





    public DrillEntity updateDrill(DrillModel drillModel) {
        return drillService.updateDrill(drillModel);
    }

    public boolean deleteDrill(String id) {
        drillService.deleteDrill(id);
        return true;
    }
}