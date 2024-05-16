package com.development.app.BasketballService.Repositories;


import com.development.app.BasketballService.Models.UserPerformanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.Optional;

public interface UserPerformanceRepository extends MongoRepository<UserPerformanceEntity, String> {
    UserPerformanceEntity findByUserId(String userId);

    Optional<UserPerformanceEntity> findByUserIdAndWorkoutDate(String userId, Date workoutDate);
}
