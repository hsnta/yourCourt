package com.basketball.user_performance.Services;

import com.basketball.codegen_service.codegen.types.UserPerformance;
import com.basketball.user_performance.Exceptions.UserPerformanceNotFoundException;
import com.basketball.user_performance.Models.UserPerformanceEntity;
import com.basketball.user_performance.Repositories.UserPerformanceRepository;
import com.basketball.user_performance.Utils.UserPerformanceUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPerformanceService {

    @Autowired
    private UserPerformanceRepository userPerformanceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserPerformance getUserPerformanceByUserId(String userId) {
        return modelMapper.map((userPerformanceRepository.findByUserId(userId).orElseThrow(() ->
                new UserPerformanceNotFoundException("User performance not found for user ID: " + userId))), UserPerformance.class);
    }

    public UserPerformance getUserPerformanceById(String id) {
        return modelMapper.map((userPerformanceRepository.findById(id).orElseThrow(() ->
                new UserPerformanceNotFoundException("User performance not found for ID: " + id))), UserPerformance.class);
    }

    public List<UserPerformance> getAllUserPerformance() {
        return userPerformanceRepository.findAll().stream()
                .map(userPerformanceEntity -> modelMapper.map(userPerformanceEntity, UserPerformance.class))
                .collect(Collectors.toList());
    }

    public UserPerformance createUserPerformance(UserPerformance userPerformance) {
        UserPerformanceEntity userPerformanceEntity = modelMapper.map(userPerformance, UserPerformanceEntity.class);
        userPerformanceEntity.setUserId(UserPerformanceUtils.createUniqueUserPerformanceId());
        updateBaseDefaultFields(userPerformanceEntity);
        return modelMapper.map((userPerformanceRepository.save(userPerformanceEntity)), UserPerformance.class);
    }

    public UserPerformance updateUserPerformance(UserPerformance userPerformance) {
        UserPerformanceEntity userPerformanceEntity = userPerformanceRepository.findById(userPerformance.getUserId())
                .orElseThrow(() -> new UserPerformanceNotFoundException("User performance not found for ID: "
                        + userPerformance.getUserPerformanceId()));
        updateBaseDefaultFields(userPerformanceEntity);
        return modelMapper.map((userPerformanceRepository.save(userPerformanceEntity)), UserPerformance.class);
    }

    public Boolean deleteUserPerformance(String userPerformanceId) {
        userPerformanceRepository.delete(userPerformanceRepository.findById(userPerformanceId).orElseThrow(() ->
                new UserPerformanceNotFoundException("User performance not found for ID: " + userPerformanceId)));
        return true;
    }

    private static void updateBaseDefaultFields(UserPerformanceEntity userEntity) {
        String userName = UserPerformanceUtils.getUserName();
        String time = UserPerformanceUtils.getCurrentSqlTime().toString();
        userEntity.setLastUpdatedBy(userName);
        userEntity.setLastUpdatedDate(time);
        if (userEntity.getCreationDate() != null) {
            userEntity.setCreatedBy(userName);
            userEntity.setCreationDate(time);
        }
    }
}
