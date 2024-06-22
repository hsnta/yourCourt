package com.basketball.user_service.Models;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WorkoutEntity {

    @Id
    private Long id;
    private String name;
    private List<String> categories;
    private List<DrillModel> drillsCompleted;
    private Date completionTime;
    private String status;
}
