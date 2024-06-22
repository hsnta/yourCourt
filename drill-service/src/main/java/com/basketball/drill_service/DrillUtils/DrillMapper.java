package com.basketball.drill_service.DrillUtils;

import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrillMapper {

    DrillEntity toEntity(DrillModel drillModel);

    DrillModel toDto(DrillEntity drillEntity);
}
