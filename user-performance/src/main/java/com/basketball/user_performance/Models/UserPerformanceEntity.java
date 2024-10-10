package com.basketball.user_performance.Models;

import com.basketball.codegen_service.codegen.types.DatabaseDefaultFields;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "USER_PERFORMANCE")
public class UserPerformanceEntity extends DatabaseDefaultFields {

    @Id
    private String userPerformanceId;
    private String userId;
    private int totalOfWorkoutsCompleted;
    private int totalOfDrillsCompleted;
}
