package com.basketball.user_performance.Controllers;

import com.basketball.user_performance.Exceptions.UserPerformanceNotFoundException;
import com.basketball.user_performance.Services.Kafka.KafkaProducerUserPerformanceService;
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

    @Autowired
    KafkaProducerUserPerformanceService kafkaProducerUserPerformanceService;

    @DgsQuery
    public UserPerformance getUserPerformanceById(@InputArgument("userPerformanceId") String userPerformanceId) {
        try {
            return userPerformanceService.getUserPerformanceById(userPerformanceId);
        } catch (UserPerformanceNotFoundException e) {
            kafkaProducerUserPerformanceService.sendMessage("User performance not found for Id: " + userPerformanceId);
        }
        return null;
    }

    @DgsQuery
    public UserPerformance getUserPerformanceByUserId(@InputArgument("userId") String userId) {
        try {
            return userPerformanceService.getUserPerformanceByUserId(userId);
        } catch (UserPerformanceNotFoundException e) {
            kafkaProducerUserPerformanceService.sendMessage("User performance not found for Id: " + userId);
        }
        return null;
    }

    @DgsQuery
    public List<UserPerformance> getAllUserPerformance() {
        try {
            return userPerformanceService.getAllUserPerformance();
        } catch (UserPerformanceNotFoundException e) {
            kafkaProducerUserPerformanceService.sendMessage("Users performances not found for");
        }
        return null;
    }

    @DgsMutation
    public UserPerformance createUserPerformance(@InputArgument("userPerformanceInput") UserPerformance userPerformance) {
        return userPerformanceService.createUserPerformance(userPerformance);
    }

    @DgsMutation
    public UserPerformance updateUserPerformance(@InputArgument("userPerformanceInput") UserPerformance userPerformance) {
        try {
            return userPerformanceService.updateUserPerformance(userPerformance);
        } catch (UserPerformanceNotFoundException e) {
            kafkaProducerUserPerformanceService.sendMessage("User performance not found for Id: " +
                    userPerformance.getUserPerformanceId());
        }
        return null;
    }

    @DgsMutation
    public Boolean deleteUserPerformance(@InputArgument("userPerformanceId") String userPerformanceId) {
        try {
            return userPerformanceService.deleteUserPerformance(userPerformanceId);
        } catch (UserPerformanceNotFoundException e) {
            kafkaProducerUserPerformanceService.sendMessage("User performance not found for Id: " + userPerformanceId);
        }
        return null;
    }

}
