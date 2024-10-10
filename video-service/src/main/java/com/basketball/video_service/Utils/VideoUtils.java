package com.basketball.video_service.Utils;

import java.sql.Date;
import java.util.UUID;

public class VideoUtils {

    public static String createUniqueVideoId() {
        return "VID_" + UUID.randomUUID();
    }

    public static String getUserName() {
        return "Maciej Chodacki";
    }

    public static Date getCurrentSqlTime() {
        return new Date(System.currentTimeMillis());
    }
}
