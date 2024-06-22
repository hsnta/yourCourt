package com.basketball.user_performance.Controllers;


import com.basketball.user_performance.Models.BeginDailyPerformanceRequest;
import com.basketball.user_performance.Models.UserPerformanceEntity;
import com.basketball.user_performance.Services.UserPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
public class UserPerformanceController {

    @Autowired
    UserPerformanceService userPerformanceService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserPerformanceEntity> getUserPerformanceById(@PathVariable String userId) {
        return ResponseEntity.ok().body(userPerformanceService.getUserPerformanceByUserId(userId));
    }

    @PostMapping("/beginDailyPerformance")
    public ResponseEntity<UserPerformanceEntity> beginDailyPerformance(
            @RequestBody BeginDailyPerformanceRequest dailyPerformanceRequest) {
        return ResponseEntity.ok().body(userPerformanceService.startDailyPerformance(dailyPerformanceRequest));
    }
}
