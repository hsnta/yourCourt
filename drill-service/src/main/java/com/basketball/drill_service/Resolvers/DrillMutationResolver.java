package com.basketball.drill_service.Resolvers;

import com.basketball.drill_service.DrillUtils.DrillMapper;
import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Models.DrillModel;
import com.basketball.drill_service.Models.ShotsTakenModel;
import com.basketball.drill_service.Repositories.DrillRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DrillMutationResolver implements GraphQLMutationResolver {

    @Autowired
    private DrillRepository drillRepository;

    private DrillMapper drillMapper;

    public DrillEntity createDrill(DrillModel drillModel) {
        DrillEntity entity = drillMapper.toEntity(drillModel);
        entity.setId(UUID.randomUUID().toString());

        return drillRepository.save(entity);
    }
}