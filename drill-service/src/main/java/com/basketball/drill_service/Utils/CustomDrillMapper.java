package com.basketball.drill_service.Utils;

import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.drill_service.Models.CustomDrillEntity;
import com.basketball.drill_service.Models.DrillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomDrillMapper {

    CustomDrillEntity toEntity(CustomDrillEntity customDrillEntity);

    Drill toDto(CustomDrillEntity customDrillEntity);
}
