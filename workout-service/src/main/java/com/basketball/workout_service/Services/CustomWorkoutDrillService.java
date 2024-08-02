package com.basketball.workout_service.Services;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import com.basketball.workout_service.Repositories.CustomWorkoutDrillRepository;
import com.basketball.workout_service.Utils.CustomWorkoutDrillMapper;
import com.basketball.workout_service.Utils.WorkoutUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomWorkoutDrillService {

    @Autowired
    CustomWorkoutDrillRepository customWorkoutDrillRepository;

    @Autowired
    CustomWorkoutDrillMapper customWorkoutDrillMapper;

    public List<DrillType> getAllCustomWorkoutDrills() {
        return customWorkoutDrillRepository.findAllByIsActiveTrue().stream()
                .map(CustomWorkoutDrillEntity::getDrillType)
                .toList();
    }

    public CustomWorkoutDrill createCustomWorkoutDrill(DrillType drillType) {
        CustomWorkoutDrillEntity customWorkoutDrillEntity = CustomWorkoutDrillEntity.builder().
                customDrillId("" + UUID.randomUUID())
                .drillType(drillType)
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
