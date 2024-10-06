package com.basketball.drill_service.Services;

import com.basketball.codegen_service.codegen.types.*;
import com.basketball.drill_service.Models.DrillIdentificationEntity;
import com.basketball.drill_service.Repositories.DrillIdentificationRepository;
import com.basketball.drill_service.Utils.DrillIdentificationMapper;
import com.basketball.drill_service.Utils.DrillUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrillIdentificationService {

    @Autowired
    DrillIdentificationRepository drillIdentificationRepository;

    @Autowired
    DrillIdentificationMapper drillIdentificationMapper;

    public DrillIdentification getDrillIdentificationByDrillType(DrillType drillType) {
        return drillIdentificationMapper.toDto(drillIdentificationRepository.findByDrillTypeAndIsActiveTrue(drillType));
    }

    public DrillIdentification createDrillIdentification(DrillIdentification drillIdentification) {
        DrillIdentificationEntity existingEntity = drillIdentificationRepository.findByDrillTypeAndIsActiveTrue(drillIdentification.getDrillType());
        if (existingEntity == null) {
            DrillIdentificationEntity drillIdentificationEntity = drillIdentificationMapper.toEntity(drillIdentification);
            drillIdentificationEntity.setDrillIdentificationId("" + UUID.randomUUID());
            drillIdentificationEntity.setIsActive(true);
            drillIdentificationEntity.setCreatedBy(DrillUtils.getUserName());
            drillIdentificationEntity.setCreationDate(DrillUtils.getCurrentSqlTime());

            updateBaseDefaultFields(drillIdentificationEntity);
            return drillIdentificationMapper.toDto(drillIdentificationRepository.save(drillIdentificationEntity));
        } else {
            return drillIdentificationMapper.toDto(existingEntity);
        }
    }

    private static void updateBaseDefaultFields(DrillIdentificationEntity drillIdentificationEntity) {
        drillIdentificationEntity.setLastUpdatedBy(DrillUtils.getUserName());
        drillIdentificationEntity.setLastUpdatedDate(DrillUtils.getCurrentSqlTime());
    }
}
