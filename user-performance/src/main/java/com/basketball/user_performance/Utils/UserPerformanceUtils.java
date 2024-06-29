package com.basketball.user_performance.Utils;

import java.sql.Date;
import java.util.UUID;

public class UserPerformanceUtils {

    public static String createUniqueUserPerformanceId() {
        return "UPE_" + UUID.randomUUID();
    }

    public static String getUserName() {
        return "Maciej Chodacki";
    }

    public static Date getCurrentSqlTime() {
        return new Date(System.currentTimeMillis());
    }
}
