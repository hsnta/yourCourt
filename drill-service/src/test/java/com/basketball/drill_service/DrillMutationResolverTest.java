package com.basketball.drill_service;

import com.basketball.drill_service.DrillUtils.DrillMapper;
import com.basketball.drill_service.Models.*;
import com.basketball.drill_service.Repositories.DrillRepository;
import com.basketball.drill_service.Resolvers.DrillMutationResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DrillMutationResolverTest {

    @Mock
    private DrillRepository drillRepository;

    @Mock
    private DrillMapper drillMapper;

    @InjectMocks
    private DrillMutationResolver drillMutationResolver;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateDrill() {
        ShotsTakenModel shotsTaken = new ShotsTakenModel();
        DrillModel drillModel = DrillModel.builder()
                .id("1")
                .workoutId("workout1")
                .userId("user1")
                .drillType(DrillType.FIVE_STAR_DRILL)
                .isSingle(true)
                .shotsTaken(shotsTaken)
                .status(DrillStatus.CREATED)
                .build();
        DrillEntity drillEntity = new DrillEntity("1", "workout1", "user1", true,
                DrillType.FIVE_STAR_DRILL, shotsTaken, DrillStatus.CREATED);

        when(drillMapper.toEntity(any())).thenReturn(drillEntity);
        when(drillRepository.save(any(DrillEntity.class))).thenReturn(drillEntity);

        DrillEntity createdDrill = drillMutationResolver.createDrill(drillModel);

        assertNotNull(createdDrill);
        verify(drillRepository, times(1)).save(any(DrillEntity.class));
    }
}
