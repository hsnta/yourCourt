package com.basketball.drill_service.Utils;

import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.drill_service.Models.DrillEntity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrillMapper {

    DrillEntity toEntity(Drill drill);

    Drill toDto(DrillEntity drillEntity);
}
