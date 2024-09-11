package com.basketball.auth_service.client;


import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.codegen_service.codegen.types.User;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClient {

    HttpGraphQlClient userServiceClient;

    public void saveNewUser(UserAuthEntity userAuthEntity, String authToken) {
        String document = """
            mutation CreateUser {
                createUser(userInput: $user) {}
            }
        """;
        HttpGraphQlClient finalClient = userServiceClient.mutate().header("Authorization", "Bearer " + authToken).build();
        finalClient.document(document).variable("user", User.newBuilder()
            .username(userAuthEntity.getUsername())
            .email(userAuthEntity.getEmail())
            .firstName(userAuthEntity.getFirstName())
            .lastName(userAuthEntity.getLastName())
            .build()).execute();
    }

}
