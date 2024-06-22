package com.basketball.user_performance.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserPerformanceEntity {

    @Id
    private String userPerformanceId;

    private String userId;

    private int totalOfWorkoutsCompleted;

    private int totalOfDrillsCompleted;

    private Date workoutDate;
}
