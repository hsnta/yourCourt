package com.basketball.workout_service.Models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class WorkoutPerformanceModel {
    private Long workoutPerformanceId;
    private Map<String, Boolean> map;

}
