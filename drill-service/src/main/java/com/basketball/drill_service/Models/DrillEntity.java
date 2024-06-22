package com.basketball.drill_service.Models;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DrillEntity {

    @Id
    String drillId;
    String workoutId;
    String userId;
    Boolean isSingle;

    @Enumerated(EnumType.STRING)
    DrillType drillType;

    @Embedded
    ShotsTakenModel shotsTaken;

    @Enumerated(EnumType.STRING)
    DrillStatus status;
}
