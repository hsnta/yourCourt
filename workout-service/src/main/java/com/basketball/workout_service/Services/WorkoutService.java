package com.basketball.workout_service.Services;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.*;
import com.basketball.workout_service.Repositories.WorkoutRepository;
import com.basketball.workout_service.Repositories.WorkoutSelectionRepository;
import com.basketball.workout_service.Utils.WorkoutUtils;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutService {

    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    WorkoutSelectionRepository workoutSelectionRepository;

    public WorkoutEntity getWorkoutById(String workoutId) {
        return workoutRepository.findById(workoutId).orElseThrow();
    }

    public List<WorkoutEntity> getAllWorkoutByUserId(String workoutId) {
        return workoutRepository.findAllByUserId(workoutId);
    }

    public List<WorkoutSelectionEntity> getAllWorkoutSelection() {
        return workoutSelectionRepository.findAll();
    }

    public WorkoutSelectionEntity createWorkoutSelection(WorkoutSelection workoutSelection) {
        WorkoutSelectionEntity workoutSelectionEntity = WorkoutSelectionEntity.builder()
                .workoutId(WorkoutUtils.createUniqueWorkoutSelectionId())
                .workoutType(workoutSelection.getWorkoutType())
                .drills(workoutSelection.getDrills())
                .createdBy(WorkoutUtils.getUserName())
                .lastUpdatedBy(WorkoutUtils.getUserName())
                .lastUpdatedDate(WorkoutUtils.getCurrentSqlTime())
                .creationDate(WorkoutUtils.getCurrentSqlTime())
                .build();
        workoutSelectionEntity.getDrills().forEach(drillModel -> drillModel.setDrillId("" + UUID.randomUUID()));

        return workoutSelectionRepository.save(workoutSelectionEntity);
    }

    public WorkoutSelectionEntity createDrillForWorkoutSelection(WorkoutSelection workoutUpdateDrill) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutUpdateDrill.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutUpdateDrill.getWorkoutId()));
        List<com.basketball.workout_service.codegen.types.DrillModel> drills = workoutUpdateDrill.getDrills();
        drills.forEach(createDrill -> {
            boolean anyMatch = workoutSelection.getDrills().stream().anyMatch(existingDrill -> existingDrill.getDrillType()
                    .equals(createDrill.getDrillType()));
            if (!anyMatch) {
                createDrill.setDrillId("" + UUID.randomUUID());
                workoutSelection.getDrills().add(createDrill);
            }
        });
        return workoutSelectionRepository.save(workoutSelection);
    }

    public WorkoutSelectionEntity updateDrillTypeInWorkoutSelection(WorkoutSelection workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        List<com.basketball.workout_service.codegen.types.DrillModel> drills = workoutSelectionModel.getDrills();
        workoutSelection.getDrills().forEach(existingDrill -> {
            drills.forEach(updateDrill -> {
                if (existingDrill.getDrillId().equals(updateDrill.getDrillId())) {
                    existingDrill.setDrillType(updateDrill.getDrillType());
                }
            });
        });
        workoutSelection.setDrills(workoutSelection.getDrills());
        return workoutSelectionRepository.save(workoutSelection);
    }

    public WorkoutSelectionEntity updateWorkoutSelection(WorkoutSelection workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        workoutSelection.setDrills(workoutSelection.getDrills());
        return workoutSelectionRepository.save(workoutSelection);
    }
}
