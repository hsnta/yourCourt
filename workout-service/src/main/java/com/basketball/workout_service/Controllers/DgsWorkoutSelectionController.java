package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Services.WorkoutSelectionService;
import com.basketball.codegen_service.codegen.types.WorkoutSelection;
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

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    @DgsQuery
    public WorkoutSelection getWorkoutSelectionById(@InputArgument("workoutSelectionId") String workoutSelectionId) {
        try {
            return workoutSelectionService.getWorkoutSelectionById(workoutSelectionId);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelectionId);
        }
        return null;
    }

    @DgsQuery
    public List<WorkoutSelection> getAllWorkoutSelection() {
        try {
            return workoutSelectionService.getAllWorkoutSelection();
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("Workouts not found");
        }
        return null;
    }

    @DgsMutation
    public WorkoutSelection createWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        try {
            return workoutSelectionService.createWorkoutSelection(workoutSelection);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("message: " + workoutSelection);
        }
        return null;
    }

    @DgsMutation
    public WorkoutSelection createDrillForWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        try {
            return workoutSelectionService.createDrillForWorkoutSelection(workoutSelection);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelection.getWorkoutId());
        }
        return null;
    }

    @DgsMutation
    public WorkoutSelection updateWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
        try {
            return workoutSelectionService.updateWorkoutSelection(workoutSelection);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelection.getWorkoutId());
        }
        return null;
    }

    @DgsMutation
    public Boolean deleteWorkoutSelection(@InputArgument("workoutSelectionId") String workoutSelectionId) {
        try {
            return workoutSelectionService.deleteWorkoutSelection(workoutSelectionId);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelectionId);
        }
        return null;
    }

}
