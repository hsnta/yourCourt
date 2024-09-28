package com.basketball.auth_service.client;


import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.codegen_service.codegen.types.UserInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient {

    @Autowired
    HttpGraphQlClient userServiceClient;

    public void saveNewUser(UserAuthEntity userAuthEntity, String authToken) {
        log.info("Sending 'createUser' request to user-service");
        String document = """
                    mutation CreateUser($user: UserInput!) {
                        createUser(userInput: $user) {userId}
                    }
                """;
        HttpGraphQlClient finalClient = userServiceClient.mutate().header("Authorization", "Bearer " + authToken).build();
        finalClient
                .document(document)
                .variable("user", UserInput.newBuilder()
                    .username(userAuthEntity.getUsername())
                    .email(userAuthEntity.getEmail())
                    .firstName(userAuthEntity.getFirstName())
                    .lastName(userAuthEntity.getLastName())
                    .build())
                .execute()
                .subscribe(
                    r -> log.info("Response from user-service: {}", r.toString()),
                    r -> log.error("Response from user-service: {}", r.toString())
        );

    }

}
