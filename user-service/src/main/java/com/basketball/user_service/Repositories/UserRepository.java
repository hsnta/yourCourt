package com.basketball.user_service.Repositories;


import com.basketball.user_service.Models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.Optional;


public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findByUserId(String userId);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByLastName(String username);
}
