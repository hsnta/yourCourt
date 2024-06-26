package com.basketball.drill_service.Services;

import com.basketball.drill_service.DrillUtils.DrillUtils;
import com.basketball.drill_service.Models.*;
import com.basketball.drill_service.Repositories.DrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrillService {

    @Autowired
    DrillRepository drillRepository;

    public DrillEntity getDrillById(String drillId) {
        return drillRepository.findById(drillId).orElseThrow();
    }

    public List<DrillEntity> getAllDrillsByWorkoutId(String workoutId) {
        return drillRepository.findAllByWorkoutId(workoutId);
    }

    public List<DrillEntity> getAllDrillsByUserId(String workoutId) {
        return drillRepository.findAllByUserId(workoutId);
    }

    public DrillEntity createDrill(DrillInput drillInput) {
        DrillEntity drillEntity = drillRepository.findById("0").orElse(
                DrillEntity.builder()
                        .drillId(DrillUtils.createUniqueDrillId())
                        .userId(drillInput.getUserId())
                        .workoutId(drillInput.getWorkoutId())
                        .drillType(drillInput.getDrillType())
                        .isSingle(drillInput.getIsSingle())
                        .shotsTaken(new ShotsTakenModel())
                        .status(DrillStatus.CREATED)
                        .build());
        return drillRepository.save(drillEntity);
    }

    public DrillEntity updateDrill(DrillModel drillModel) {
        DrillEntity drillEntity = drillRepository.findById(drillModel.getId()).orElseThrow();
        drillEntity.setStatus(drillModel.getStatus());
        return drillRepository.save(drillEntity);
    }

    public void deleteDrill(String id) {
        DrillEntity drillEntity = drillRepository.findById(id).orElseThrow();
        drillRepository.delete(drillEntity);
    }
}
