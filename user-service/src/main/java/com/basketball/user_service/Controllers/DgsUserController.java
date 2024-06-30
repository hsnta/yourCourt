package com.basketball.user_service.Controllers;

import com.basketball.user_service.Exceptions.UserNotFoundException;
import com.basketball.user_service.Services.Kafka.KafkaProducerUserService;
import com.basketball.user_service.Services.UserService;
import com.basketball.user_service.codegen.types.User;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DgsComponent
public class DgsUserController {

    @Autowired
    UserService userService;

    @Autowired
    KafkaProducerUserService kafkaProducerUserService;

    @DgsQuery
    public User getUserById(@InputArgument("userId") String userId) {
        try {
            return userService.getUserById(userId);
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("User not found for ID: " + userId);
        }
        return null;
    }

    @DgsQuery
    public User getUserByUsername(@InputArgument("username") String username) {
        try {
            return userService.getUserByUsername(username);
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("User not found for username: " + username);
        }
        return null;
    }

    @DgsQuery
    public User getUserByEmail(@InputArgument("email") String email) {
        try {
            return userService.getUserByEmail(email);
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("User not found for email: " + email);
        }
        return null;
    }

    @DgsQuery
    public List<User> getAllUser() {
        try {
            return userService.getAllUsers();
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("Users not found");
        }
        return null;
    }

    @DgsMutation
    public User createUser(@InputArgument("userInput") User user) {
        return userService.createUser(user);
    }

    @DgsMutation
    public User updateUser(@InputArgument("userInput") User user) {
        try {
            return userService.updateUser(user);
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("User not found for id: " + user.getUserId());
        }
        return null;
    }

    @DgsMutation
    public Boolean deleteUser(@InputArgument("userId") String userId) {
        try {
            return userService.deleteUser(userId);
        } catch (UserNotFoundException e) {
            kafkaProducerUserService.sendMessage("User not found for id: " + userId);
        }
        return null;
    }

}
