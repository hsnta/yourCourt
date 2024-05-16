package com.development.app.BasketballService.Models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//Data will be stored for every user for maximum 30 days
//Database call should be executed for last 30 days per user
//Considering using OneToMany to connect users table with users performance table

@Data
@Builder
public class UserPerformanceEntity {

    @Id
    private String id;

    private String userId;

    private int totalOfWorkoutsCompleted;

    private int totalOfDrillsCompleted;

    private Date workoutDate;

    private List<WorkoutModel> workouts;
    //templated object

}
