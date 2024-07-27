package com.basketball.drill_service.Models;

import com.basketball.drill_service.codegen.types.DrillStatus;
import com.basketball.drill_service.codegen.types.DrillType;
import com.basketball.drill_service.codegen.types.ShotsTaken;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "DRILLS")
public class DrillEntity extends DatabaseDefaultFields {

    @Id
    String drillId;

    String workoutId;
    String userId;
    Boolean isSingle;

    @Enumerated(EnumType.STRING)
    DrillType drillType;

    @Embedded
    ShotsTaken shotsToBeTaken;
    @Embedded
    ShotsTaken shotsMade;
    @Embedded
    ShotsTaken shotsRequired;

    String timer;

    @Enumerated(EnumType.STRING)
    DrillStatus status;
}
