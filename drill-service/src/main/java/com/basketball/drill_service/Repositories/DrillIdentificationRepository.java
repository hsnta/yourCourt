package com.basketball.drill_service.Repositories;


import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.drill_service.Models.DrillIdentificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface DrillIdentificationRepository extends MongoRepository<DrillIdentificationEntity, String> {

    Optional<DrillIdentificationEntity> findByDrillType(DrillType drillType);

}
