package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Services.WorkoutSelectionService;
import com.basketball.workout_service.codegen.types.WorkoutSelection;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class DgsWorkoutSelectionController {

    @Autowired
    WorkoutSelectionService workoutSelectionService;

    @DgsQuery
    public WorkoutSelection getWorkoutSelectionById(@InputArgument("workoutSelectionId") String workoutSelectionId) {
        return workoutSelectionService.getWorkoutSelectionById(workoutSelectionId);
    }

    @DgsQuery
    public List<WorkoutSelection> getAllWorkoutSelection() {
        return workoutSelectionService.getAllWorkoutSelection();
    }
}
