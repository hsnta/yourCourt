package com.basketball.drill_service.Models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DrillModel {
    String id;
    String workoutId;
    String userId;
    Boolean isSingle;
    DrillType drillType;
    ShotsTakenModel shotsTaken;
    DrillStatus status;
}

