package com.basketball.drill_service.DrillUtils;

import java.util.UUID;

public class DrillUtils {

    public static String createUniqueDrillId() {
        return "DRL_" + UUID.randomUUID();
    }
}
