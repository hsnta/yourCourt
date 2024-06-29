package com.basketball.workout_service.Utils;

import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import com.basketball.workout_service.codegen.types.WorkoutSelection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutSelectionEntity toEntity(WorkoutSelection workoutSelection);

    WorkoutSelection toDto(WorkoutSelectionEntity workoutSelectionEntity);
}

