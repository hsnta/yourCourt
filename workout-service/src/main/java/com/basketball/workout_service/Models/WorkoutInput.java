package com.basketball.workout_service.Models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WorkoutInput {
    private String userId;
    private String name;
    private List<WorkoutType> categories;
    private Date completionTime;
    private String status;
}
