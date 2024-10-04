package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.codegen_service.codegen.types.CustomWorkoutDrillInput;
import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import com.basketball.workout_service.Repositories.CustomWorkoutDrillRepository;
import com.basketball.workout_service.Utils.CustomWorkoutDrillMapper;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomWorkoutDrillService {

    @Autowired
    CustomWorkoutDrillRepository customWorkoutDrillRepository;

    @Autowired
    CustomWorkoutDrillMapper customWorkoutDrillMapper;

    public List<CustomWorkoutDrill> getAllCustomWorkoutDrills() {
        return customWorkoutDrillRepository.findAllByIsActiveTrue().stream()
                .map(customWorkoutDrillMapper::toDto)
                .toList();
    }

    public CustomWorkoutDrill createCustomWorkoutDrill(CustomWorkoutDrillInput customWorkoutDrillInput) {
        Boolean drillAlreadyExists = customWorkoutDrillRepository.existsByCategoriesInAndAndDrillTypeAndDrillDifficultyAndIsActiveTrue(
                customWorkoutDrillInput.getCategories(), customWorkoutDrillInput.getDrillType(), customWorkoutDrillInput.getDrillDifficulty());
        if (drillAlreadyExists) {
            return null;
        }
        CustomWorkoutDrillEntity customWorkoutDrillEntity = CustomWorkoutDrillEntity.builder()
                .customDrillId("" + UUID.randomUUID())
                .drillType(customWorkoutDrillInput.getDrillType())
                .drillName(customWorkoutDrillInput.getDrillName())
                .drillDifficulty(customWorkoutDrillInput.getDrillDifficulty())
                .categories(customWorkoutDrillInput.getCategories())
                .tags(customWorkoutDrillInput.getTags())
                .description(customWorkoutDrillInput.getDescription())
                .isActive(true)
                .createdBy(WorkoutUtils.getUserName())
                .creationDate(WorkoutUtils.getCurrentSqlTime())
                .build();
        updateBaseDefaultFields(customWorkoutDrillEntity);

        return customWorkoutDrillMapper.toDto(customWorkoutDrillRepository.save(customWorkoutDrillEntity));
    }

    private static void updateBaseDefaultFields(CustomWorkoutDrillEntity customWorkoutDrillEntity) {
        customWorkoutDrillEntity.setLastUpdatedBy(WorkoutUtils.getUserName());
        customWorkoutDrillEntity.setLastUpdatedDate(WorkoutUtils.getCurrentSqlTime());
    }
}
