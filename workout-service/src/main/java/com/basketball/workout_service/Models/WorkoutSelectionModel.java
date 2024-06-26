package com.basketball.workout_service.Models;

import com.basketball.workout_service.codegen.types.DrillModel;
import com.basketball.workout_service.codegen.types.WorkoutType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WorkoutSelectionModel {

    private String workoutId;
    private WorkoutType workoutType;
    private List<DrillModel> drills;
    private Date lastUpdatedDate;
    private Date creationDate;
    private String lastUpdatedBy;
    private String createdBy;
}
