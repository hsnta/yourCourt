package com.basketball.drill_service.Models;

import com.basketball.codegen_service.codegen.types.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
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
    String drillName;

    @Enumerated(EnumType.STRING)
    DrillDifficulty drillDifficulty;
    @Enumerated(EnumType.STRING)
    List<WorkoutType> categories;
    List<Tags> tags;
    String description;

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
