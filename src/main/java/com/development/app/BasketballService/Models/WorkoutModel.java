package com.development.app.BasketballService.Models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class WorkoutModel {
    private Long id;
    private String name;
    private List<String> categories;
    private List<DrillModel> drillsCompleted;
    private Date completionTime;
    private String status;
}
