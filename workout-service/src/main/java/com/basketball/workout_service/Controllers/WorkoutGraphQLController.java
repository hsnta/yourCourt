package com.basketball.workout_service.Controllers;


import com.basketball.workout_service.Models.WorkoutEntity;
import com.basketball.workout_service.Models.WorkoutInput;
import com.basketball.workout_service.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WorkoutGraphQLController {

    @Autowired
    private WorkoutService workoutService;


    public WorkoutEntity getWorkoutById(@Argument String workoutId) {
        return workoutService.getWorkoutById(workoutId);
    }


    public List<WorkoutEntity> getAllWorkoutsByUserId(@Argument String userId) {
        return workoutService.getAllWorkoutByUserId(userId);
    }


    public WorkoutEntity createWorkout(@Argument WorkoutInput workoutInput) {
        return workoutService.createWorkout(workoutInput);
    }

//    public WorkoutEntity updateWorkout(WorkoutModel workoutModel) {
//        return workoutService.updateWorkout(workoutModel);
//    }

    public boolean deleteWorkout(String id) {
        workoutService.deleteWorkout(id);
        return true;
    }
}