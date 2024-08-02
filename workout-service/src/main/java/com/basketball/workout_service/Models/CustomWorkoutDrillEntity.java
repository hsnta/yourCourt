package com.basketball.workout_service.Models;

import com.basketball.codegen_service.codegen.types.DrillType;
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
@Document(collection = "CUSTOM_WORKOUT_DRILL")
public class CustomWorkoutDrillEntity extends DatabaseDefaultFields {

    @Id
    private String customDrillId;
    private DrillType drillType;
}
