package com.basketball.workout_service.Models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DatabaseDefaultFields {

    Timestamp lastUpdatedDate;
    Timestamp creationDate;
    String lastUpdatedBy;
    String createdBy;
}
