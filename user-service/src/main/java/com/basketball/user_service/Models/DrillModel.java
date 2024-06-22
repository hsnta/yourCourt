package com.basketball.user_service.Models;

import lombok.Data;

@Data
public class DrillModel {
    private String drillId;
    private String workoutId;
    private String userId;
    private DrillType drillType;
    private Boolean isSingle;
    private ShotsTakenModel shotsTaken;
    private DrillStatus status;
}