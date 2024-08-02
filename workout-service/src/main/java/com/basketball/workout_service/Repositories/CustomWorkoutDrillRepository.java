package com.basketball.workout_service.Repositories;

import com.basketball.workout_service.Models.CustomWorkoutDrillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CustomWorkoutDrillRepository extends MongoRepository<CustomWorkoutDrillEntity, String> {

    List<CustomWorkoutDrillEntity> findAllByIsActiveTrue();

}
