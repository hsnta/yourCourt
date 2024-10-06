package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.codegen_service.codegen.types.CustomWorkoutDrillInput;
import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import com.basketball.workout_service.Repositories.CustomWorkoutDrillRepository;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomWorkoutDrillService {

    @Autowired
    CustomWorkoutDrillRepository customWorkoutDrillRepository;

    @Autowired
    ModelMapper modelMapper;

    public List<CustomWorkoutDrill> getAllCustomWorkoutDrills() {
        return customWorkoutDrillRepository.findAll().stream()
                .map(customWorkoutDrillEntity -> modelMapper.map(customWorkoutDrillEntity, CustomWorkoutDrill.class))
                .toList();
    }

    public CustomWorkoutDrill createCustomWorkoutDrill(CustomWorkoutDrillInput customWorkoutDrillInput) {
        Boolean drillAlreadyExists = customWorkoutDrillRepository.existsByCategoriesInAndAndDrillTypeAndDrillDifficulty(
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
                .build();
        updateBaseDefaultFields(customWorkoutDrillEntity);

        return modelMapper.map(customWorkoutDrillRepository.save(customWorkoutDrillEntity), CustomWorkoutDrill.class);
    }

    private static void updateBaseDefaultFields(CustomWorkoutDrillEntity customWorkoutDrillEntity) {
        String userName = WorkoutUtils.getUserName();
        String time = WorkoutUtils.getCurrentSqlTime().toString();
        customWorkoutDrillEntity.setLastUpdatedBy(userName);
        customWorkoutDrillEntity.setLastUpdatedDate(time);
        if (customWorkoutDrillEntity.getCreationDate() != null) {
            customWorkoutDrillEntity.setCreatedBy(userName);
            customWorkoutDrillEntity.setCreationDate(time);
        }
    }
}
