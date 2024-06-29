package com.basketball.drill_service.Utils;

import java.sql.Date;
import java.util.UUID;

public class DrillUtils {

    public static String createUniqueDrillId() {
        return "DRL_" + UUID.randomUUID();
    }

    public static String getUserName() {
        return "Maciej Chodacki";
    }

    public static Date getCurrentSqlTime() {
        return new Date(System.currentTimeMillis());
    }
}
