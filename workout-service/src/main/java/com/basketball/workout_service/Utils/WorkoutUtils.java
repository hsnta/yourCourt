package com.basketball.workout_service.Utils;

import java.sql.Date;
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
