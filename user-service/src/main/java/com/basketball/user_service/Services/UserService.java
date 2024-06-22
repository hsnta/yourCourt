package com.basketball.user_service.Services;
import com.basketball.user_service.Models.UserEntity;
import com.basketball.user_service.Models.UserModel;
import com.basketball.user_service.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public UserEntity getUserByLastname(String lastname) {
        return userRepository.findByLastName(lastname).orElseThrow();
    }

    public UserEntity createUser(UserModel userModel) {
        UserEntity userEntity = userRepository.findById(userModel.getUserId()).orElse(
                UserEntity.builder()
                        .userId(userModel.getUserId())
                        .email(userModel.getEmail())
                        .firstName(userModel.getFirstName())
                        .lastName(userModel.getLastName())
                        .phoneNumber(userModel.getPhoneNumber())
                        .dateOfBirth(userModel.getDateOfBirth())
                        .username(userModel.getUsername())
                        .build());
        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(UserModel userModel) {
        UserEntity userEntity = userRepository.findById(userModel.getUserId()).orElseThrow();
        return userRepository.save(userEntity);
    }

    public void deleteUser(String id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userRepository.delete(userEntity);
    }
}
