package com.basketball.user_service.Models;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserModel {

    private String userId;
    private Long firstName;
    private Long lastName;
    private Long dateOfBirth;
    private Long email;
    private Long phoneNumber;
    private String username;
}
