package com.basketball.drill_service.Models;

import com.basketball.codegen_service.codegen.types.DrillDifficulty;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.codegen_service.codegen.types.ShotsTaken;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "DRILL_IDENTIFICATION")
public class DrillIdentificationEntity extends DatabaseDefaultFields {

    @Id
    String drillIdentificationId;

    Boolean isSingle;

    @Enumerated(EnumType.STRING)
    DrillType drillType;

    @Embedded
    ShotsTaken shotsToBeTaken;

    String timer;

    DrillDifficulty drillDifficulty;
}
