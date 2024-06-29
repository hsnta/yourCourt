package com.basketball.user_performance.Services;

import com.basketball.user_performance.Exceptions.UserPerformanceNotFoundException;
import com.basketball.user_performance.Models.UserPerformanceEntity;
import com.basketball.user_performance.Repositories.UserPerformanceRepository;
import com.basketball.user_performance.Utils.UserPerformanceMapper;
import com.basketball.user_performance.Utils.UserPerformanceUtils;
import com.basketball.workout_service.codegen.types.UserPerformance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPerformanceService {

    @Autowired
    private UserPerformanceRepository userPerformanceRepository;

    @Autowired
    private UserPerformanceMapper userPerformanceMapper;

    public UserPerformance getUserPerformanceByUserId(String userId) {
        return userPerformanceMapper.toDto(userPerformanceRepository.findByUserId(userId).orElseThrow(() ->
                new UserPerformanceNotFoundException("User performance not found for user ID: " + userId)));
    }

    public UserPerformance getUserPerformanceById(String id) {
        return userPerformanceMapper.toDto(userPerformanceRepository.findById(id).orElseThrow(() ->
                new UserPerformanceNotFoundException("User performance not found for ID: " + id)));
    }

    public List<UserPerformance> getAllUserPerformance() {
        return userPerformanceRepository.findAll().stream()
                .map(userPerformanceMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserPerformance createUserPerformance(UserPerformance userPerformance) {
        UserPerformanceEntity userPerformanceEntity = userPerformanceMapper.toEntity(userPerformance);
        userPerformanceEntity.setUserId(UserPerformanceUtils.createUniqueUserPerformanceId());
        userPerformanceEntity.setCreatedBy(UserPerformanceUtils.getUserName());
        userPerformanceEntity.setCreationDate(UserPerformanceUtils.getCurrentSqlTime());
        updateBaseDefaultFields(userPerformanceEntity);
        return userPerformanceMapper.toDto(userPerformanceRepository.save(userPerformanceEntity));
    }

    public UserPerformance updateUserPerformance(UserPerformance userPerformance) {
        UserPerformanceEntity userPerformanceEntity = userPerformanceRepository.findById(userPerformance.getUserId())
                .orElseThrow(() -> new UserPerformanceNotFoundException("User performance not found for ID: "
                        + userPerformance.getUserPerformanceId()));
        updateBaseDefaultFields(userPerformanceEntity);
        return userPerformanceMapper.toDto(userPerformanceRepository.save(userPerformanceEntity));
    }

    public Boolean deleteUserPerformance(String userPerformanceId) {
        userPerformanceRepository.findById(userPerformanceId).orElseThrow(() ->
                        new UserPerformanceNotFoundException("User performance not found for ID: " + userPerformanceId))
                .setIsActive(false);
        return true;
    }

    private static void updateBaseDefaultFields(UserPerformanceEntity userEntity) {
        userEntity.setLastUpdatedBy(UserPerformanceUtils.getUserName());
        userEntity.setLastUpdatedDate(UserPerformanceUtils.getCurrentSqlTime());
    }
}
