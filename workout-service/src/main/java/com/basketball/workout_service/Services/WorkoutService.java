package com.basketball.workout_service.Services;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.*;
import com.basketball.workout_service.Repositories.WorkoutRepository;
import com.basketball.workout_service.Repositories.WorkoutSelectionRepository;
import com.basketball.workout_service.WorkoutUtils.WorkoutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        List<DrillModel> drills = workoutUpdateDrillsModel.getDrills();
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

    public WorkoutEntity createWorkout(WorkoutInput workoutInput) {
        return new WorkoutEntity();
    }

    public void updateWorkout(WorkoutModel workoutModel) {

    }
    public void deleteWorkout(String workoutId) {

    }

}
