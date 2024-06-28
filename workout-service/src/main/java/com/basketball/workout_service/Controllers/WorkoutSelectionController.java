package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import com.basketball.workout_service.Services.WorkoutService;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/workout")
public class WorkoutSelectionController {

    @Autowired
    WorkoutService workoutService;

    @GetMapping(value = "/getAllWorkoutSelection")
    public ResponseEntity<List<WorkoutSelectionEntity>> getAllWorkoutSelection() {
        return ResponseEntity.ok().body(workoutService.getAllWorkoutSelection());
    }

    @PostMapping(value = "/createWorkoutSelection")
    public ResponseEntity<WorkoutSelectionEntity> createWorkoutSelection(
            @RequestBody WorkoutSelection workoutSelectionModel) {
        return ResponseEntity.ok().body(workoutService.createWorkoutSelection(workoutSelectionModel));
    }

    @PostMapping(value = "/createDrillForWorkoutSelection")
    public ResponseEntity<WorkoutSelectionEntity> createDrillForWorkoutSelection(
            @RequestBody WorkoutSelection workoutSelectionModel) {
        return ResponseEntity.ok().body(workoutService.createDrillForWorkoutSelection(workoutSelectionModel));
    }
}
