package com.basketball.workout_service.WorkoutUtils;

import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import com.basketball.workout_service.Models.WorkoutSelectionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    WorkoutSelectionEntity toEntity(WorkoutSelectionModel drillModel);

    WorkoutSelectionModel toDto(WorkoutSelectionEntity drillEntity);
}
