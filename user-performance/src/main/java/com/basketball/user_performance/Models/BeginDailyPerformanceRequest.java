package com.basketball.user_performance.Models;

import lombok.Data;

import java.util.Date;

@Data
public class BeginDailyPerformanceRequest {
    private String userId;
    private Date workoutDate;
}
