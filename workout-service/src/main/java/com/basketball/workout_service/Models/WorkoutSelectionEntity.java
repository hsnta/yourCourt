package com.basketball.workout_service.Models;



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
@Document(collection = "WORKOUT_SELECTION")
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutSelectionEntity extends DatabaseDefaultFields{

    @Id
    private String workoutId;
    private WorkoutType workoutType;
    private List<DrillModel> drills;
}
