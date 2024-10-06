package com.basketball.user_service.Models;

import com.basketball.codegen_service.codegen.types.DatabaseDefaultFields;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "USER")
public class UserEntity extends DatabaseDefaultFields {

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private Integer phoneNumber;
    private String username;
}
