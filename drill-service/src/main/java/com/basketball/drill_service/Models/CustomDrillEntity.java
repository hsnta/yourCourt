package com.basketball.drill_service.Models;

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
@Document(collection = "CUSTOM_DRILLS")
public class CustomDrillEntity extends DatabaseDefaultFields {

    @Id
    String drillId;

    Boolean isSingle;

    @Enumerated(EnumType.STRING)
    DrillType drillType;

    @Embedded
    ShotsTaken shotsToBeTaken;

    String timer;
}
