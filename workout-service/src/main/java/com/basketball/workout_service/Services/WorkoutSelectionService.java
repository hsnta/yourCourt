package com.basketball.workout_service.Services;

import com.basketball.workout_service.Exceptions.WorkoutNotFoundException;
import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import com.basketball.workout_service.Repositories.WorkoutSelectionRepository;
import com.basketball.workout_service.WorkoutUtils.WorkoutMapper;
import com.basketball.workout_service.WorkoutUtils.WorkoutUtils;
import com.basketball.workout_service.codegen.types.DrillModel;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkoutSelectionService {

    @Autowired
    WorkoutSelectionRepository workoutSelectionRepository;

    @Autowired
    WorkoutMapper workoutMapper;

    public WorkoutSelection getWorkoutSelectionById(String id) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(id).orElseThrow(() ->
                new WorkoutNotFoundException("Workout selection not found for ID: " + id));
        return workoutMapper.toDto(workoutSelectionEntity);
    }

    public List<WorkoutSelection> getAllWorkoutSelection() {
        List<WorkoutSelectionEntity> selectionEntities = workoutSelectionRepository.findAll();
        return selectionEntities.stream()
                .map(workoutSelectionEntity -> workoutMapper.toDto(workoutSelectionEntity))
                .collect(Collectors.toList());
    }

    public WorkoutSelection createWorkoutSelection(WorkoutSelection workoutSelection) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutMapper.toEntity(workoutSelection);
        workoutSelectionEntity.setWorkoutId(WorkoutUtils.createUniqueWorkoutSelectionId());
        workoutSelectionEntity.getDrills().forEach(drillModel -> drillModel.setDrillId("" + UUID.randomUUID()));
        workoutSelectionEntity.setIsActive(true);
        workoutSelectionEntity.setCreatedBy(WorkoutUtils.getUserName());
        workoutSelectionEntity.setCreationDate(WorkoutUtils.getCurrentSqlTime());
        updateBaseDefaultFields(workoutSelectionEntity);
        workoutSelectionRepository.save(workoutSelectionEntity);
        return workoutMapper.toDto(workoutSelectionEntity);
    }

    public WorkoutSelection createDrillForWorkoutSelection(WorkoutSelection workoutUpdateDrillsModel) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(workoutUpdateDrillsModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutUpdateDrillsModel.getWorkoutId()));
        List<DrillModel> drills = workoutUpdateDrillsModel.getDrills();
        drills.forEach(createDrill -> {
            boolean anyMatch = workoutSelectionEntity.getDrills().stream().anyMatch(existingDrill -> existingDrill.getDrillType()
                    .equals(createDrill.getDrillType()));
            if (!anyMatch) {
                createDrill.setDrillId("" + UUID.randomUUID());
                workoutSelectionEntity.getDrills().add(createDrill);
            }
        });
        workoutSelectionRepository.save(workoutSelectionEntity);
        return workoutMapper.toDto(workoutSelectionEntity);
    }

    public WorkoutSelectionEntity updateDrillTypeInWorkoutSelection(WorkoutSelection workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        List<DrillModel> drills = workoutSelectionModel.getDrills();
        workoutSelectionEntity.getDrills().forEach(existingDrill -> {
            drills.forEach(updateDrill -> {
                if (existingDrill.getDrillId().equals(updateDrill.getDrillId())) {
                    existingDrill.setDrillType(updateDrill.getDrillType());
                }
            });
        });
        workoutSelectionEntity.setDrills(workoutSelectionEntity.getDrills());
        return workoutSelectionRepository.save(workoutSelectionEntity);
    }

    public WorkoutSelection updateWorkoutSelection(WorkoutSelection workoutSelectionModel) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(workoutSelectionModel.getWorkoutId())
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " +
                        workoutSelectionModel.getWorkoutId()));
        workoutSelectionEntity.setDrills(workoutSelectionEntity.getDrills());
        workoutSelectionEntity.getDrills().forEach(drillModel -> drillModel.setDrillId("" + UUID.randomUUID()));
        workoutSelectionRepository.save(workoutSelectionEntity);
        return workoutMapper.toDto(workoutSelectionEntity);
    }

    public Boolean deleteWorkoutSelection(String workoutSelectionId) {
        WorkoutSelectionEntity workoutSelectionEntity = workoutSelectionRepository.findById(workoutSelectionId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout selection not found for ID: " + workoutSelectionId));
        workoutSelectionEntity.setIsActive(false);
        workoutSelectionRepository.save(workoutSelectionEntity);
        return true;
    }

    private static void updateBaseDefaultFields(WorkoutSelectionEntity workoutSelectionEntity) {
        workoutSelectionEntity.setLastUpdatedBy(WorkoutUtils.getUserName());
        workoutSelectionEntity.setLastUpdatedDate(WorkoutUtils.getCurrentSqlTime());
    }
}
