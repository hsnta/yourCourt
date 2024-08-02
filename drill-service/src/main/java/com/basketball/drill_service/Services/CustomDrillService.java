package com.basketball.drill_service.Services;

import com.basketball.codegen_service.codegen.types.*;
import com.basketball.drill_service.Exceptions.DrillNotFoundException;
import com.basketball.drill_service.Models.CustomDrillEntity;
import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Repositories.CustomDrillRepository;
import com.basketball.drill_service.Repositories.DrillRepository;
import com.basketball.drill_service.Utils.CustomDrillMapper;
import com.basketball.drill_service.Utils.DrillMapper;
import com.basketball.drill_service.Utils.DrillUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomDrillService {

    @Autowired
    CustomDrillRepository customDrillRepository;

    @Autowired
    CustomDrillMapper customDrillMapper;

    public Drill getCustomDrillByDrillType(DrillType drillType) {
        return customDrillMapper.toDto(customDrillRepository.findByDrillTypeAndIsActiveTrue(drillType));
    }

//    public void createCustomDrill(CustomDrillInput customDrillInput) {
//        CustomDrillEntity customDrillEntity = customDrillMapper.toEntity(drill);
//        drillEntity.setDrillId(DrillUtils.createUniqueDrillId());
//        drillEntity.setIsActive(true);
//        drillEntity.setCreatedBy(DrillUtils.getUserName());
//        drillEntity.setCreationDate(DrillUtils.getCurrentSqlTime());
//        drillEntity.setShotsRequired(new ShotsTaken());
//        drillEntity.setShotsToBeTaken(new ShotsTaken());
//        drillEntity.setShotsMade(new ShotsTaken());
//        updateBaseDefaultFields(drillEntity);
//        return drillMapper.toDto(drillRepository.save(drillEntity));
//    }

    private static void updateBaseDefaultFields(DrillEntity drillEntity) {
        drillEntity.setLastUpdatedBy(DrillUtils.getUserName());
        drillEntity.setLastUpdatedDate(DrillUtils.getCurrentSqlTime());
    }
}
