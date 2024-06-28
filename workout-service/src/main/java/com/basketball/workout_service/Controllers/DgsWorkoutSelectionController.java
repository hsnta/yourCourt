package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Services.WorkoutSelectionService;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
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

    @DgsMutation
    public WorkoutSelection createWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        return workoutSelectionService.createWorkoutSelection(workoutSelection);
    }

    @DgsMutation
    public WorkoutSelection createDrillForWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        return workoutSelectionService.createDrillForWorkoutSelection(workoutSelection);
    }

    @DgsMutation
    public WorkoutSelection updateWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        return workoutSelectionService.updateWorkoutSelection(workoutSelection);
    }

    @DgsMutation
    public Boolean deleteWorkoutSelection(@InputArgument("workoutSelectionId") String workoutSelectionId) {
        return workoutSelectionService.deleteWorkoutSelection(workoutSelectionId);
    }

}
