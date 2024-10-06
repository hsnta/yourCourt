package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.*;
import com.basketball.codegen_service.codegen.types.WorkoutType;
import com.basketball.workout_service.Models.*;
import com.basketball.workout_service.Repositories.WorkoutRepository;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    @Autowired
    ModelMapper modelMapper;

    public WorkoutEntity getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow();
    }

    public List<WorkoutEntity> getAllWorkoutsByUserId(String userId) {
        return workoutRepository.findAllByUserId(userId);
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
                .build();
        updateBaseDefaultFields(workoutEntity);

        DrillCreationRequest drillCreationRequest = DrillCreationRequest.newBuilder()
                .workoutId(workoutEntity.getWorkoutId())
                .userId(workoutEntity.getUserId())
                .customWorkoutDrills(customWorkoutDrillsRequest.getCustomWorkoutDrills().stream()
                        .map(customWorkoutDrillInput -> modelMapper.map(customWorkoutDrillInput, CustomWorkoutDrill.class))
                        .toList())
                .build();

        workoutRepository.save(workoutEntity);
        kafkaProducerWorkoutService.sendDrillsToDrillService(drillCreationRequest);
        return workoutEntity;
    }
    private static void updateBaseDefaultFields(WorkoutEntity workoutEntity) {
        String userName = WorkoutUtils.getUserName();
        String time = WorkoutUtils.getCurrentSqlTime().toString();
        workoutEntity.setLastUpdatedBy(userName);
        workoutEntity.setLastUpdatedDate(time);
        if (workoutEntity.getCreationDate() != null) {
            workoutEntity.setCreatedBy(userName);
            workoutEntity.setCreationDate(time);
        }
    }
}


