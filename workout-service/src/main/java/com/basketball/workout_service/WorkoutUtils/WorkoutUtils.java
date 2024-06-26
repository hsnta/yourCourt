package com.basketball.workout_service.WorkoutUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class WorkoutUtils {

    public static String createUniqueWorkoutId() {
        return "WRK_" + UUID.randomUUID();
    }

    public static String createUniqueWorkoutSelectionId() {
        return "" + UUID.randomUUID();
    }

    public static String getUserName() {
        return "Maciej Chodacki";
    }

    public static Date getCurrentSqlTime() {
        return new Date(System.currentTimeMillis());
    }
}
