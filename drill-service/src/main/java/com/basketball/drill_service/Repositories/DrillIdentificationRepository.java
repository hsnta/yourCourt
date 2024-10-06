package com.basketball.drill_service.Repositories;


import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.drill_service.Models.DrillIdentificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DrillIdentificationRepository extends MongoRepository<DrillIdentificationEntity, String> {

    DrillIdentificationEntity findByDrillTypeAndIsActiveTrue(DrillType drillType);

}
