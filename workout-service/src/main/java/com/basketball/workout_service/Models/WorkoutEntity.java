package com.basketball.workout_service.Models;

import com.basketball.codegen_service.codegen.types.DatabaseDefaultFields;
import com.basketball.codegen_service.codegen.types.WorkoutType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
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
