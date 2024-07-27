package com.basketball.workout_service.Models;

import com.basketball.workout_service.codegen.types.WorkoutType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "WORKOUT")
public class WorkoutEntity extends DatabaseDefaultFields {

    @Id
    private String workoutId;
    private String userId;
    private String name;
    private WorkoutType workoutType;
    private Date completionTime;
    private String status;
}
