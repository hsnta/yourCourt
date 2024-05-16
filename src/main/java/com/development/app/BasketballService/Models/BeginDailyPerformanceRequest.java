package com.development.app.BasketballService.Models;

import lombok.Data;

import java.util.Date;

@Data
public class BeginDailyPerformanceRequest {
    private String userId;
    private Date workoutDate;
    private WorkoutModel workoutModel;
}
