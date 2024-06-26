package com.basketball.drill_service.Models;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Drills")
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
