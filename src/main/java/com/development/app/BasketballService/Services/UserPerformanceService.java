package com.development.app.BasketballService.Services;

import com.development.app.BasketballService.Models.BeginDailyPerformanceRequest;
import com.development.app.BasketballService.Models.UserPerformanceEntity;
import com.development.app.BasketballService.Repositories.UserPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPerformanceService {

    @Autowired
    private UserPerformanceRepository userPerformanceRepository;

    public UserPerformanceEntity getUserPerformanceByUserId(String userId) {
        return userPerformanceRepository.findByUserId(userId);
    }

    public UserPerformanceEntity startDailyPerformance(BeginDailyPerformanceRequest beginDailyPerformanceRequest) {
        UserPerformanceEntity userPerformanceEntity = userPerformanceRepository.findByUserIdAndWorkoutDate(beginDailyPerformanceRequest.getUserId(),
                beginDailyPerformanceRequest.getWorkoutDate()).orElse(UserPerformanceEntity.builder()
                .userId(beginDailyPerformanceRequest.getUserId())
                .workoutDate(beginDailyPerformanceRequest.getWorkoutDate())
                .totalOfWorkoutsCompleted(0)
                .totalOfDrillsCompleted(0)
                .workouts(List.of(beginDailyPerformanceRequest.getWorkoutModel()))
                .build());
        return userPerformanceRepository.save(userPerformanceEntity);
    }


}
