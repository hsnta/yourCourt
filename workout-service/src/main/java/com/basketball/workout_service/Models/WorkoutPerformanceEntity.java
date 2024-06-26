package com.basketball.workout_service.Models;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "WORKOUT_PERFORMANCE")
public class WorkoutPerformanceEntity {

    @Id
    String workoutPerformanceId;

    String workoutType;
    String drillType;
    Boolean isSelected;
}
