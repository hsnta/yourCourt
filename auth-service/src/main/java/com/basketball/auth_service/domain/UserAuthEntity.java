package com.basketball.auth_service.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "USER_AUTH_DETAILS")
public class UserAuthEntity {
    @Id
    String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String email;
    String firstName;
    String lastName;
    Set<String> roles;

}
