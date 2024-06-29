package com.basketball.workout_service.Models;

import com.basketball.workout_service.codegen.types.DrillModel;
import com.basketball.workout_service.codegen.types.WorkoutType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "WORKOUT_SELECTION")
public class WorkoutSelectionEntity extends DatabaseDefaultFields{

    @Id
    private String workoutId;
    private WorkoutType workoutType;
    private List<DrillModel> drills;
}
