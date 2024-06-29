package com.basketball.user_performance.Controllers;

import com.basketball.user_performance.Services.UserPerformanceService;
import com.basketball.workout_service.codegen.types.UserPerformance;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class DgsUserPerformanceController {

    @Autowired
    UserPerformanceService userPerformanceService;

    @DgsQuery
    public UserPerformance getUserPerformanceById(@InputArgument("userPerformanceId") String userPerformanceId) {
        return userPerformanceService.getUserPerformanceById(userPerformanceId);
    }

    @DgsQuery
    public UserPerformance getUserPerformanceByUserId(@InputArgument("userId") String userId) {
        return userPerformanceService.getUserPerformanceByUserId(userId);
    }

    @DgsQuery
    public List<UserPerformance> getAllUserPerformance() {
        return userPerformanceService.getAllUserPerformance();
    }

    @DgsMutation
    public UserPerformance createUserPerformance(@InputArgument("userPerformanceInput") UserPerformance userPerformance) {
        return userPerformanceService.createUserPerformance(userPerformance);
    }

    @DgsMutation
    public UserPerformance updateUserPerformance(@InputArgument("userPerformanceInput") UserPerformance userPerformance) {
        return userPerformanceService.updateUserPerformance(userPerformance);
    }

    @DgsMutation
    public Boolean deleteUserPerformance(@InputArgument("userPerformanceId") String userPerformanceId) {
        return userPerformanceService.deleteUserPerformance(userPerformanceId);
    }

}
