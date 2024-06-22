package com.basketball.workout_service.Models;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class WorkoutEntity extends DatabaseDefaultFields{

    @Id
    private Long workoutId;
    private Long userId;
    private String name;
    private List<String> categories;
    private Date completionTime;
    private String status;
}
