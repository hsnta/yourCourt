package com.basketball.workout_service.Repositories;

import com.basketball.workout_service.Models.WorkoutEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface WorkoutRepository extends MongoRepository<WorkoutEntity, String> {
    List<WorkoutEntity> findAllByUserId(String userId);
}
