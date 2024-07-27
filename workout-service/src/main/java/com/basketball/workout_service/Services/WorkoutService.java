package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.basketball.codegen_service.codegen.types.WorkoutBySelection;
import com.basketball.codegen_service.codegen.types.WorkoutSelection;
import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.*;
import com.basketball.workout_service.Repositories.WorkoutRepository;
import com.basketball.workout_service.Repositories.WorkoutSelectionRepository;
import com.basketball.workout_service.Services.Kafka.KafkaProducerWorkoutService;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WorkoutSelectionRepository workoutSelectionRepository;

    @Autowired
    KafkaProducerWorkoutService kafkaProducerWorkoutService;

    public WorkoutEntity getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow();
    }

    public List<WorkoutEntity> getAllWorkoutByUserId(String workoutId) {
        return workoutRepository.findAllByUserId(workoutId);
    }

    public List<WorkoutSelectionEntity> getAllWorkouts() {
        return workoutSelectionRepository.findAll();
    }

    public List<WorkoutEntity> createWorkoutByWorkoutSelection(WorkoutBySelection workoutBySelectionInput) {
        List<WorkoutSelection> workoutSelectionsWithEnabledDrills = workoutBySelectionInput.getWorkoutSelectionList().stream()
                .filter(workoutSelection -> workoutSelection.getDrills().stream()
                        .anyMatch(drill -> Boolean.TRUE.equals(drill.getIsEnabled())))
                .toList();
        List<WorkoutEntity> workoutEntityList = new ArrayList<>();
        workoutSelectionsWithEnabledDrills.forEach(workoutSelection -> {
            WorkoutEntity workoutEntity = WorkoutEntity.builder()
                    .workoutId(WorkoutUtils.createUniqueWorkoutId())
                    .userId(workoutBySelectionInput.getUserId())
                    .workoutType(workoutSelection.getWorkoutType())
                    .status("CREATED")
                    .createdBy(workoutBySelectionInput.getUserId())
                    .creationDate(WorkoutUtils.getCurrentSqlTime())
                    .build();
            updateBaseDefaultFields(workoutEntity);
            workoutEntityList.add(workoutEntity);
            DrillCreationRequest drillCreationRequest = DrillCreationRequest.newBuilder()
                    .workoutId(workoutEntity.getWorkoutId())
                    .userId(workoutEntity.getUserId())
                    .drills(workoutSelection.getDrills())
                    .build();
            kafkaProducerWorkoutService.sendDrillsToDrillService(drillCreationRequest);
            workoutRepository.save(workoutEntity);
        });
        return workoutEntityList;
    }

    private static void updateBaseDefaultFields(WorkoutEntity workoutEntity) {
        workoutEntity.setLastUpdatedBy(WorkoutUtils.getUserName());
        workoutEntity.setLastUpdatedDate(WorkoutUtils.getCurrentSqlTime());
    }
}


