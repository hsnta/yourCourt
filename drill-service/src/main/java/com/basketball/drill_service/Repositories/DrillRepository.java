package com.basketball.drill_service.Repositories;


import com.basketball.drill_service.Models.DrillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface DrillRepository extends MongoRepository<DrillEntity, String> {

    List<DrillEntity> findAllByWorkoutId(String workoutId);

    List<DrillEntity> findAllByUserId(String userId);
}
