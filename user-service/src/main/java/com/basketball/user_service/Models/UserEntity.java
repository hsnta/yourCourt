package com.basketball.user_service.Models;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USER")
public class UserEntity extends DatabaseDefaultFields{

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private Integer phoneNumber;
    private String username;
    private String password;
}
