package com.basketball.user_service.Services;
import com.basketball.codegen_service.codegen.types.User;
import com.basketball.user_service.Exceptions.UserNotFoundException;
import com.basketball.user_service.Models.UserEntity;
import com.basketball.user_service.Utils.UserMapper;
import com.basketball.user_service.Repositories.UserRepository;
import com.basketball.user_service.Utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public User getUserById(String id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + id)));
    }

    public User getUserByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(() ->
                new UserNotFoundException("User not found for username: " + username)));
    }

    public User getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found for email: " + email)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setUserId(UserUtils.createUniqueUserId());
        userEntity.setCreatedBy(UserUtils.getUserName());
        userEntity.setCreationDate(UserUtils.getCurrentSqlTime());
        updateBaseDefaultFields(userEntity);
        return userMapper.toDto(userRepository.save(userEntity));
    }

    public User updateUser(User user) {
        UserEntity userEntity = userRepository.findById(user.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + user.getUserId()));
        updateBaseDefaultFields(userEntity);
        return userMapper.toDto(userRepository.save(userEntity));
    }

    public Boolean deleteUser(String userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User not found for ID: " + userId))
                .setIsActive(false);
        return true;
    }

    private static void updateBaseDefaultFields(UserEntity userEntity) {
        userEntity.setLastUpdatedBy(UserUtils.getUserName());
        userEntity.setLastUpdatedDate(UserUtils.getCurrentSqlTime());
    }
}
