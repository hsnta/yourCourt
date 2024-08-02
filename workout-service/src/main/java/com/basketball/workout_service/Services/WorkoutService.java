package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.*;
import com.basketball.codegen_service.codegen.types.WorkoutType;
import com.basketball.workout_service.Models.*;
import com.basketball.workout_service.Repositories.WorkoutRepository;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    public WorkoutEntity getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow();
    }

    public List<WorkoutEntity> getAllWorkoutsByUserId(String userId) {
        return workoutRepository.findAllByUserIdAndIsActiveTrue(userId);
    }

    public List<WorkoutEntity> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public WorkoutEntity createWorkoutByCustomWorkoutDrills(CustomWorkoutDrillsRequest customWorkoutDrillsRequest) {
        WorkoutEntity workoutEntity = WorkoutEntity.builder()
                .workoutId(WorkoutUtils.createUniqueWorkoutId())
                .userId(customWorkoutDrillsRequest.getUserId())
                .name(customWorkoutDrillsRequest.getWorkoutName())
                .workoutType(WorkoutType.CUSTOM_WORKOUT)
                .status("CREATED")
                .createdBy(customWorkoutDrillsRequest.getUserId())
                .creationDate(WorkoutUtils.getCurrentSqlTime())
                .build();
        updateBaseDefaultFields(workoutEntity);

        DrillCreationRequest drillCreationRequest = DrillCreationRequest.newBuilder()
                .workoutId(workoutEntity.getWorkoutId())
                .userId(workoutEntity.getUserId())
                .drillTypes(customWorkoutDrillsRequest.getDrillTypes())
                .build();

        workoutRepository.save(workoutEntity);
        kafkaProducerWorkoutService.sendDrillsToDrillService(drillCreationRequest);
        return workoutEntity;
    }
    private static void updateBaseDefaultFields(WorkoutEntity workoutEntity) {
        workoutEntity.setLastUpdatedBy(WorkoutUtils.getUserName());
        workoutEntity.setLastUpdatedDate(WorkoutUtils.getCurrentSqlTime());
    }
}


