package com.basketball.drill_service.Models;

import lombok.Data;

@Data
public class DrillInput {
    String workoutId;
    String userId;
    Boolean isSingle;
    DrillType drillType;
    DrillStatus status;
}
