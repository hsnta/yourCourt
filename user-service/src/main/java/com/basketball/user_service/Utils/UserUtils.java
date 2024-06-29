package com.basketball.user_service.Utils;

import java.sql.Date;
import java.util.UUID;

public class UserUtils {

    public static String createUniqueUserId() {
        return "USR_" + UUID.randomUUID();
    }

    public static String getUserName() {
        return "Maciej Chodacki";
    }

    public static Date getCurrentSqlTime() {
        return new Date(System.currentTimeMillis());
    }
}
