package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Models.WorkoutModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/workout")
public class WorkoutController {

    @GetMapping(value = "/exampleWorkout")
    public ResponseEntity<WorkoutModel> getWorkoutEntity() {
        return ResponseEntity.ok().body(WorkoutModel.builder().workoutId(123L).name("workout").build());
    }
}
