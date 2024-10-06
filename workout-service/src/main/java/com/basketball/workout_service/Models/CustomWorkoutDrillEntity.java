package com.basketball.workout_service.Models;

import com.basketball.codegen_service.codegen.types.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CUSTOM_WORKOUT_DRILL")
public class CustomWorkoutDrillEntity extends DatabaseDefaultFields {

    @Id
    private String customDrillId;
    private DrillType drillType;
    private String drillName;
    private DrillDifficulty drillDifficulty;
    private List<WorkoutType> categories;
    private List<Tags> tags;
    private String description;
}
