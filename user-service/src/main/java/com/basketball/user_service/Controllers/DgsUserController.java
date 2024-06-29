package com.basketball.user_service.Controllers;

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

    @DgsQuery
    public User getUserById(@InputArgument("userId") String userId) {
        return userService.getUserById(userId);
    }

    @DgsQuery
    public User getUserByUsername(@InputArgument("username") String username) {
        return userService.getUserByUsername(username);
    }

    @DgsQuery
    public User getUserByEmail(@InputArgument("email") String email) {
        return userService.getUserByEmail(email);
    }

    @DgsQuery
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @DgsMutation
    public User createUser(@InputArgument("userInput") User user) {
        return userService.createUser(user);
    }

    @DgsMutation
    public User updateUser(@InputArgument("userInput") User user) {
        return userService.updateUser(user);
    }

    @DgsMutation
    public Boolean deleteUser(@InputArgument("userId") String userId) {
        return userService.deleteUser(userId);
    }

}
