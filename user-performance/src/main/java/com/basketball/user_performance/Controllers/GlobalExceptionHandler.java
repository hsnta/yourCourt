package com.basketball.user_performance.Controllers;

import com.basketball.user_performance.Exceptions.UserPerformanceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserPerformanceNotFoundException.class)
    public ResponseEntity<String> handleUserPerformanceNotFoundException(UserPerformanceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
