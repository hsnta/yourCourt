package com.basketball.drill_service.Controllers;


import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import com.basketball.drill_service.Services.DrillService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DrillGraphQLController implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private DrillService drillService;

    public DrillEntity getDrillById(String id) {
        return drillService.getDrillById(id);
    }

    public List<DrillEntity> getAllDrillsByWorkoutId(String workoutId) {
        return drillService.getAllDrillsByWorkoutId(workoutId);
    }

    public List<DrillEntity> getAllDrillsByUserId(String userId) {
        return drillService.getAllDrillsByUserId(userId);
    }

    public DrillEntity createDrill(DrillModel drillModel) {
        return drillService.createDrill(drillModel);
    }

    public DrillEntity updateDrill(DrillModel drillModel) {
        return drillService.updateDrill(drillModel);
    }

    public boolean deleteDrill(String id) {
        drillService.deleteDrill(id);
        return true;
    }
}