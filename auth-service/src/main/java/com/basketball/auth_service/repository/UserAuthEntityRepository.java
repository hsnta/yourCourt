package com.basketball.auth_service.repository;

import com.basketball.auth_service.domain.UserAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserAuthEntityRepository extends MongoRepository<UserAuthEntity, String> {
    Optional<UserAuthEntity> findByUsername(String username);
}
