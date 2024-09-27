package com.basketball.auth_service.client;


import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.codegen_service.codegen.types.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.ClientGraphQlResponse;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient {

    @Autowired
    HttpGraphQlClient userServiceClient;

    public void saveNewUser(UserAuthEntity userAuthEntity, String authToken) {
        // TODO document is incorrect
        String document = """
            mutation CreateUser {
                createUser(userInput: $user) {}
            }
        """;
        HttpGraphQlClient finalClient = userServiceClient.mutate().header("Authorization", "Bearer " + authToken).build();
        Mono<ClientGraphQlResponse> response = finalClient.document(document).variable("user", User.newBuilder()
            .username(userAuthEntity.getUsername())
            .email(userAuthEntity.getEmail())
            .firstName(userAuthEntity.getFirstName())
            .lastName(userAuthEntity.getLastName())
            .build()).execute();

        response.subscribe(r -> log.info(r.toString()));
    }

}
