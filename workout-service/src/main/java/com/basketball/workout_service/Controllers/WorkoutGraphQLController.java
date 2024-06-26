package com.basketball.workout_service.Controllers;


import com.basketball.workout_service.Models.WorkoutEntity;
import com.basketball.workout_service.Models.WorkoutInput;
import com.basketball.workout_service.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WorkoutGraphQLController {

    @Autowired
    private WorkoutService workoutService;


    public WorkoutEntity getWorkoutById(String workoutId) {
        return workoutService.getWorkoutById(workoutId);
    }


    public List<WorkoutEntity> getAllWorkoutsByUserId(String userId) {
        return workoutService.getAllWorkoutByUserId(userId);
    }


    public WorkoutEntity createWorkout(WorkoutInput workoutInput) {
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