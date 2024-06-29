package com.basketball.user_service.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseDefaultFields {

    Boolean isActive;
    Date lastUpdatedDate;
    Date creationDate;
    String lastUpdatedBy;
    String createdBy;
}
