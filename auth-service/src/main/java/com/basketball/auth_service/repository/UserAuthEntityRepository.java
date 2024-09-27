package com.basketball.auth_service.repository;

import com.basketball.auth_service.domain.UserAuthEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserAuthEntityRepository extends MongoRepository<UserAuthEntity, String> {
    Optional<UserAuthEntity> findByUsername(String username);

    @Aggregation(pipeline = {
            "{ $match: { 'username' : ?0 } }",
            "{ $project: { 'logoutCounter' : 1, '_id': 0 } }"
    })
    Optional<Integer> getLogoutCounterByUserName(String username);
}
