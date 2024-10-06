package com.basketball.drill_service.Services;

import com.basketball.codegen_service.codegen.types.DrillIdentification;
import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.drill_service.Models.DrillIdentificationEntity;
import com.basketball.drill_service.Repositories.DrillIdentificationRepository;
import com.basketball.drill_service.Utils.DrillUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrillIdentificationService {

    @Autowired
    DrillIdentificationRepository drillIdentificationRepository;

    @Autowired
    ModelMapper modelMapper;

    public DrillIdentification getDrillIdentificationByDrillType(DrillType drillType) {
        return modelMapper.map(drillIdentificationRepository.findByDrillType(drillType).orElseThrow(), DrillIdentification.class);
    }

    public DrillIdentification createDrillIdentification(DrillIdentification drillIdentification) {
        DrillIdentificationEntity drillIdentificationEntity = modelMapper.map(drillIdentification, DrillIdentificationEntity.class);
        drillIdentificationEntity.setDrillIdentificationId("" + UUID.randomUUID());
        updateBaseDefaultFields(drillIdentificationEntity);
        return modelMapper.map(drillIdentificationRepository.save(drillIdentificationEntity), DrillIdentification.class);

    }

    private static void updateBaseDefaultFields(DrillIdentificationEntity drillIdentificationEntity) {
        String userName = DrillUtils.getUserName();
        String time = DrillUtils.getCurrentSqlTime().toString();
        drillIdentificationEntity.setLastUpdatedBy(userName);
        drillIdentificationEntity.setLastUpdatedDate(time);
        if (drillIdentificationEntity.getCreationDate() != null) {
            drillIdentificationEntity.setCreatedBy(userName);
            drillIdentificationEntity.setCreationDate(time);
        }
    }
}
