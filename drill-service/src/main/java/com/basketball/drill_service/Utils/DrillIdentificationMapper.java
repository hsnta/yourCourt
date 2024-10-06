package com.basketball.drill_service.Utils;

import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.codegen_service.codegen.types.DrillIdentification;
import com.basketball.drill_service.Models.DrillIdentificationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrillIdentificationMapper {

    DrillIdentificationEntity toEntity(DrillIdentification drillIdentification);

    DrillIdentification toDto(DrillIdentificationEntity drillIdentificationEntity);
}
