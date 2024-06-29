package com.basketball.drill_service.Services;

import com.basketball.drill_service.Utils.DrillMapper;
import com.basketball.drill_service.Utils.DrillUtils;
import com.basketball.drill_service.Exceptions.DrillNotFoundException;
import com.basketball.drill_service.Models.*;
import com.basketball.drill_service.Repositories.DrillRepository;
import com.basketball.drill_service.codegen.types.Drill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrillService {

    @Autowired
    DrillRepository drillRepository;

    @Autowired
    DrillMapper drillMapper;

    public Drill getDrillById(String drillId) {
        return drillMapper.toDto(drillRepository.findById(drillId).orElseThrow());
    }

    public List<Drill> getAllDrillsByWorkoutId(String workoutId) {
        return drillRepository.findAllByWorkoutId(workoutId).stream()
                .map(drillMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<Drill> getAllDrillsByUserId(String workoutId) {
        return drillRepository.findAllByUserId(workoutId).stream()
                .map(drillMapper::toDto)
                .collect(Collectors.toList());
    }

    public Drill createDrill(Drill drill) {
        DrillEntity drillEntity = drillMapper.toEntity(drill);
        drillEntity.setDrillId(DrillUtils.createUniqueDrillId());
        drillEntity.setIsActive(true);
        drillEntity.setCreatedBy(DrillUtils.getUserName());
        drillEntity.setCreationDate(DrillUtils.getCurrentSqlTime());
        updateBaseDefaultFields(drillEntity);
        return drillMapper.toDto(drillRepository.save(drillEntity));
    }

    public Drill updateDrill(Drill drill) {
        DrillEntity drillEntity = drillRepository.findById(drill.getDrillId()).orElseThrow(() ->
                new DrillNotFoundException("Drill not found for ID: " + drill.getDrillId()));
        updateBaseDefaultFields(drillEntity);
        return drillMapper.toDto(drillRepository.save(drillEntity));
    }

    public Boolean deleteDrill(String id) {
        drillRepository.findById(id).orElseThrow(() ->
                        new DrillNotFoundException("Drill not found for ID: " + id))
                .setIsActive(false);
        return true;
    }

    private static void updateBaseDefaultFields(DrillEntity drillEntity) {
        drillEntity.setLastUpdatedBy(DrillUtils.getUserName());
        drillEntity.setLastUpdatedDate(DrillUtils.getCurrentSqlTime());
    }
}
