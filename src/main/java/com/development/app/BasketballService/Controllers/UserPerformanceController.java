package com.development.app.BasketballService.Controllers;

import com.development.app.BasketballService.Models.BeginDailyPerformanceRequest;
import com.development.app.BasketballService.Models.UserPerformanceEntity;
import com.development.app.BasketballService.Services.UserPerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance")
@SpringBootApplication
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
