package com.basketball.auth_service.repository;

import com.basketball.auth_service.domain.UserAuthEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserAuthEntityRepository extends MongoRepository<UserAuthEntity, String> {
    Optional<UserAuthEntity> findByUsername(String username);

    @Query(value = "{ 'username' : ?0 }", fields = "{'logoutCounter': 0}")
    Optional<Integer> getLogoutCounterByUserName(String userName);
}
