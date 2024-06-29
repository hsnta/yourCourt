package com.basketball.drill_service.Exceptions;

public class DrillNotFoundException extends RuntimeException {
    public DrillNotFoundException(String message) {
        super(message);
    }
}
