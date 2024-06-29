package com.basketball.user_performance.Utils;

import com.basketball.user_performance.Models.UserPerformanceEntity;
import com.basketball.workout_service.codegen.types.UserPerformance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPerformanceMapper {

    UserPerformanceEntity toEntity(UserPerformance userPerformance);

    UserPerformance toDto(UserPerformanceEntity userPerformanceEntity);
}

