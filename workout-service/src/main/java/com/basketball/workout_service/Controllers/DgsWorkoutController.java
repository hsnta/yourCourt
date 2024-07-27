package com.basketball.workout_service.Controllers;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.WorkoutEntity;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Services.WorkoutSelectionService;
import com.basketball.workout_service.Services.WorkoutService;
import com.basketball.workout_service.codegen.types.WorkoutBySelection;
import com.basketball.workout_service.codegen.types.WorkoutBySelectionInput;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class DgsWorkoutController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    @DgsMutation
    public List<WorkoutEntity> createWorkoutByWorkoutSelection(@InputArgument("workoutSelectionInput")
                                                                   WorkoutBySelection workoutSelectionInput) {
        try {
            return workoutService.createWorkoutByWorkoutSelection(workoutSelectionInput);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessage("message: " + workoutSelectionInput);
        }
        return null;
    }

//    @DgsMutation
//    public WorkoutSelection createDrillForWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
//        try {
//            return workoutSelectionService.createDrillForWorkoutSelection(workoutSelection);
//        } catch (WorkoutNotFoundException e) {
//            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelection.getWorkoutId());
//        }
//        return null;
//    }
//
//    @DgsMutation
//    public WorkoutSelection updateWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
//        try {
//            return workoutSelectionService.updateWorkoutSelection(workoutSelection);
//        } catch (WorkoutNotFoundException e) {
//            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelection.getWorkoutId());
//        }
//        return null;
//    }
//
//    @DgsMutation
//    public Boolean deleteWorkoutSelection(@InputArgument("workoutSelectionId") String workoutSelectionId) {
//        try {
//            return workoutSelectionService.deleteWorkoutSelection(workoutSelectionId);
//        } catch (WorkoutNotFoundException e) {
//            kafkaProducerWorkoutService.sendMessage("Workout not found for id: " + workoutSelectionId);
//        }
//        return null;
//    }

}
