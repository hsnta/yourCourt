package com.basketball.workout_service.Utils;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomWorkoutDrillMapper {

    CustomWorkoutDrillEntity toEntity(CustomWorkoutDrill customWorkoutDrill);

    CustomWorkoutDrill toDto(CustomWorkoutDrillEntity customWorkoutDrillEntity);
}

