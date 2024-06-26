package com.basketball.workout_service.Services;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import com.basketball.workout_service.Models.WorkoutSelectionModel;
import com.basketball.workout_service.Repositories.WorkoutSelectionRepository;
import com.basketball.workout_service.WorkoutUtils.WorkoutUtils;
import com.basketball.workout_service.codegen.types.DrillModel;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkoutSelectionService {


    @Autowired
    WorkoutSelectionRepository workoutSelectionRepository;

    public WorkoutSelection getWorkoutSelectionById(String id) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(id).orElseThrow(() ->
                new WorkoutNotFoundException("Workout selection not found for ID: " + id));
        return WorkoutSelection.newBuilder().workoutId(workoutSelectionEntity.getWorkoutId())
                .workoutType(workoutSelectionEntity.getWorkoutType())
                .drills(workoutSelectionEntity.getDrills())
                .build();

    }

    public List<WorkoutSelection> getAllWorkoutSelection() {
        return List.of(new WorkoutSelection());
    }

    public WorkoutSelectionEntity createWorkoutSelection(WorkoutSelectionModel workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelectionEntity = WorkoutSelectionEntity.builder()
                .workoutId(WorkoutUtils.createUniqueWorkoutSelectionId())
                .workoutType(workoutSelectionModel.getWorkoutType())
                .drills(workoutSelectionModel.getDrills())
                .createdBy(WorkoutUtils.getUserName())
                .lastUpdatedBy(WorkoutUtils.getUserName())
                .lastUpdatedDate(WorkoutUtils.getCurrentSqlTime())
                .creationDate(WorkoutUtils.getCurrentSqlTime())
                .build();
        workoutSelectionEntity.getDrills().forEach(drillModel -> drillModel.setDrillId("" + UUID.randomUUID()));

        return workoutSelectionRepository.save(workoutSelectionEntity);
    }

    public WorkoutSelectionEntity createDrillForWorkoutSelection(WorkoutSelectionModel workoutUpdateDrillsModel) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutUpdateDrillsModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutUpdateDrillsModel.getWorkoutId()));
        List<com.basketball.workout_service.codegen.types.DrillModel> drills = workoutUpdateDrillsModel.getDrills();
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

    public WorkoutSelectionEntity updateDrillTypeInWorkoutSelection(WorkoutSelectionModel workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        List<DrillModel> drills = workoutSelectionModel.getDrills();
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

    public WorkoutSelectionEntity updateWorkoutSelection(WorkoutSelectionModel workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelection = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        workoutSelection.setDrills(workoutSelection.getDrills());
        return workoutSelectionRepository.save(workoutSelection);
    }
}
