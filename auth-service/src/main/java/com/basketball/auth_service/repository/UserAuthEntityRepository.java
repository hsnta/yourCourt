package com.basketball.auth_service.repository;

import domain.UserAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthEntityRepository extends MongoRepository<UserAuthEntity, String> {
}
