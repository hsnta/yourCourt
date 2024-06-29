package com.basketball.user_performance.Repositories;

import com.basketball.user_performance.Models.UserPerformanceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.Optional;


public interface UserPerformanceRepository extends MongoRepository<UserPerformanceEntity, String> {
    Optional<UserPerformanceEntity> findByUserId(String userId);

    Optional<UserPerformanceEntity> findByUserIdAndCreationDate(String userId, Date creationDate);
}
