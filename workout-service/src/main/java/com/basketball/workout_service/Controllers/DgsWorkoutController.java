package com.basketball.workout_service.Controllers;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrillsRequest;
import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.WorkoutEntity;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Services.WorkoutService;
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


    @DgsQuery
    public WorkoutEntity getWorkoutById(@InputArgument("workoutId") String workoutId) {
        try {
            return workoutService.getWorkoutById(workoutId);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("message: " + workoutId);
        }
        return null;
    }

    @DgsQuery
    public List<WorkoutEntity> getAllWorkoutsByUserId(@InputArgument("userId") String userId) {
        try {
            return workoutService.getAllWorkoutsByUserId(userId);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("message: " + userId);
        }
        return null;
    }

    @DgsMutation
    public WorkoutEntity createWorkoutByCustomWorkoutDrills(@InputArgument("customWorkoutDrills")
                                                                  CustomWorkoutDrillsRequest customWorkoutDrills) {
        try {
            return workoutService.createWorkoutByCustomWorkoutDrills(customWorkoutDrills);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("message: " + customWorkoutDrills);
        }
        return null;
    }

    @DgsMutation
    public WorkoutEntity createWorkoutFromRecommendation(@InputArgument("recommendation")
                                                            CustomWorkoutDrillsRequest customWorkoutDrills) {
        try {
            return workoutService.createWorkoutByCustomWorkoutDrills(customWorkoutDrills);
        } catch (WorkoutNotFoundException e) {
            kafkaProducerWorkoutService.sendMessageToUI("message: " + customWorkoutDrills);
        }
        return null;
    }
}
