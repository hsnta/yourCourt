package com.basketball.user_performance.Models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "USER_PERFORMANCE")
public class UserPerformanceEntity extends DatabaseDefaultFields{

    @Id
    private String userPerformanceId;
    private String userId;
    private int totalOfWorkoutsCompleted;
    private int totalOfDrillsCompleted;
}
