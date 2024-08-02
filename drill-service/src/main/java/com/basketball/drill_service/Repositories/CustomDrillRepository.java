package com.basketball.drill_service.Repositories;


import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.drill_service.Models.CustomDrillEntity;
import com.basketball.drill_service.Models.DrillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface CustomDrillRepository extends MongoRepository<CustomDrillEntity, String> {

    CustomDrillEntity findByDrillTypeAndIsActiveTrue(DrillType drillType);

}
