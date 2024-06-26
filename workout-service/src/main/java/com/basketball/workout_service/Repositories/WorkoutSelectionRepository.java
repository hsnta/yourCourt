package com.basketball.workout_service.Repositories;

import com.basketball.workout_service.Models.WorkoutSelectionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface WorkoutSelectionRepository extends MongoRepository<WorkoutSelectionEntity, String> {

}
