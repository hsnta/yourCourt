package com.basketball.user_service.Services;
import com.basketball.codegen_service.codegen.types.User;
import com.basketball.user_service.Exceptions.UserNotFoundException;
import com.basketball.user_service.Models.UserEntity;
import com.basketball.user_service.Repositories.UserRepository;
import com.basketball.user_service.Utils.UserUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public User getUserById(String id) {
        return modelMapper.map((userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + id))), User.class);
    }

    public User getUserByUsername(String username) {
        return modelMapper.map((userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User not found for username: " + username))), User.class);
    }

    public User getUserByEmail(String email) {
        return modelMapper.map((userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found for email: " + email))), User.class);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setUserId(UserUtils.createUniqueUserId());
        updateBaseDefaultFields(userEntity);
        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    public User updateUser(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + user.getUserId()));
        updateBaseDefaultFields(userEntity);
        return modelMapper.map(userRepository.save(userEntity), User.class);
    }

    public Boolean deleteUser(String userId) {
        userRepository.delete(userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + userId)));

        return true;
    }

    private static void updateBaseDefaultFields(UserEntity userEntity) {
        String userName = UserUtils.getUserName();
        String time = UserUtils.getCurrentSqlTime().toString();
        userEntity.setLastUpdatedBy(userName);
        userEntity.setLastUpdatedDate(time);
        if (userEntity.getCreationDate() != null) {
            userEntity.setCreatedBy(userName);
            userEntity.setCreationDate(time);
        }
    }
}
