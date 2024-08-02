package com.basketball.workout_service.Controllers;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Services.CustomWorkoutDrillService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class DgsCustomWorkoutDrillController {

    @Autowired
    CustomWorkoutDrillService customWorkoutDrillService;

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    @DgsQuery
    public List<DrillType> getAllCustomWorkoutDrills() {
        try {
            return customWorkoutDrillService.getAllCustomWorkoutDrills();
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("Drills not found");
        }
        return null;
    }

    @DgsMutation
    public CustomWorkoutDrill createCustomWorkoutDrill(@InputArgument("drillType") DrillType drillType) {
        try {
            return customWorkoutDrillService.createCustomWorkoutDrill(drillType);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("message: " + drillType);
        }
        return null;
    }

//    @DgsMutation
//    public WorkoutSelection createWorkoutSelection(@InputArgument("workoutSelectionInput") WorkoutSelection workoutSelection) {
//        try {
//            return workoutSelectionService.createWorkoutSelection(workoutSelection);
//        } catch (WorkoutNotFoundException e) {
//            kafkaProducerWorkoutService.sendMessage("message: " + workoutSelection);
//        }
//        return null;
//    }
//
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
