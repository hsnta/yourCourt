package com.basketball.workout_service.Models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WorkoutModel {
    private Long workoutId;
    private Long userId;
    private String name;
    private List<String> categories;
    private Date completionTime;
    private String status;
}
