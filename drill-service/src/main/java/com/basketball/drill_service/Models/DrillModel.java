package com.basketball.drill_service.Models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DrillModel {
    String id;
    String workoutId;
    String userId;
    Boolean isSingle;
    DrillType drillType;
    DrillStatus status;
}

