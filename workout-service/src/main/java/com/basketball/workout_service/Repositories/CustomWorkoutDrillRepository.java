package com.basketball.workout_service.Repositories;

import com.basketball.codegen_service.codegen.types.DrillDifficulty;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.codegen_service.codegen.types.WorkoutType;
import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;


public interface CustomWorkoutDrillRepository extends MongoRepository<CustomWorkoutDrillEntity, String> {

    Boolean existsByCategoriesInAndAndDrillTypeAndDrillDifficulty(List<WorkoutType> categories,
                                                                              DrillType drillType, DrillDifficulty drillDifficulty);
}
