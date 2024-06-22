package com.basketball.drill_service.Services;

import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import com.basketball.drill_service.Models.DrillStatus;
import com.basketball.drill_service.Repositories.DrillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrillService {

    @Autowired
    DrillRepository drillRepository;

    public DrillEntity getDrillById(String id) {
        return drillRepository.findById(id).orElseThrow();
    }

    public List<DrillEntity> getAllDrillsByWorkoutId(String workoutId) {
        return drillRepository.findAllByWorkoutId(workoutId);
    }

    public List<DrillEntity> getAllDrillsByUserId(String workoutId) {
        return drillRepository.findAllByUserId(workoutId);
    }

    public DrillEntity createDrill(DrillModel drillModel) {
        DrillEntity drillEntity = drillRepository.findById(drillModel.getId()).orElse(
                DrillEntity.builder()
                        .userId(drillModel.getUserId())
                        .workoutId(drillModel.getWorkoutId())
                        .drillType(drillModel.getDrillType())
                        .isSingle(drillModel.getIsSingle())
                        .shotsTaken(drillModel.getShotsTaken())
                        .status(DrillStatus.CREATED)
                        .build());
        return drillRepository.save(drillEntity);
    }

    public DrillEntity updateDrill(DrillModel drillModel) {
        DrillEntity drillEntity = drillRepository.findById(drillModel.getId()).orElseThrow();
        drillEntity.setStatus(drillModel.getStatus());
        drillEntity.setShotsTaken(drillModel.getShotsTaken());
        return drillRepository.save(drillEntity);
    }

    public void deleteDrill(String id) {
        DrillEntity drillEntity = drillRepository.findById(id).orElseThrow();
        drillRepository.delete(drillEntity);
    }
}
