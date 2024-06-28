package com.basketball.workout_service.Models;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutEntity extends DatabaseDefaultFields {

    @Id
    private Long workoutId;
    private Long userId;
    private String name;
    private List<WorkoutType> categories;
    private Date completionTime;
    private String status;
}
