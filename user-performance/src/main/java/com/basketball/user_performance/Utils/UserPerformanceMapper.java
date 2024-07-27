package com.basketball.user_performance.Utils;

import com.basketball.codegen_service.codegen.types.UserPerformance;
import com.basketball.user_performance.Models.UserPerformanceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPerformanceMapper {

    UserPerformanceEntity toEntity(UserPerformance userPerformance);

    UserPerformance toDto(UserPerformanceEntity userPerformanceEntity);
}

