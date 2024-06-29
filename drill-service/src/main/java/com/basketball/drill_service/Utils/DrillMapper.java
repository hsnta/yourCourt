package com.basketball.drill_service.Utils;

import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.codegen.types.Drill;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrillMapper {

    DrillEntity toEntity(Drill drill);

    Drill toDto(DrillEntity drillEntity);
}
