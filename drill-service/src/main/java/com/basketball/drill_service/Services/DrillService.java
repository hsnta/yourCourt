package com.basketball.drill_service.Services;

import com.basketball.codegen_service.codegen.types.CustomWorkoutDrill;
import com.basketball.codegen_service.codegen.types.Drill;
import com.basketball.codegen_service.codegen.types.DrillCreationRequest;
import com.basketball.codegen_service.codegen.types.ShotsTaken;
import com.basketball.drill_service.Exceptions.DrillNotFoundException;
import com.basketball.drill_service.Models.DrillEntity;
import com.basketball.drill_service.Repositories.DrillRepository;
import com.basketball.drill_service.Utils.DrillUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrillService {

    @Autowired
    DrillRepository drillRepository;

    @Autowired
    ModelMapper modelMapper;

    public Drill getDrillById(String drillId) {
        return modelMapper.map(drillRepository.findById(drillId).orElseThrow(), Drill.class);
    }

    public List<Drill> getAllDrillsByWorkoutId(String workoutId) {
        return drillRepository.findAllByWorkoutId(workoutId).stream()
                .map(drillEntity -> modelMapper.map(drillEntity, Drill.class))
                .collect(Collectors.toList());
    }

    public List<Drill> getAllDrillsByUserId(String workoutId) {
        return drillRepository.findAllByUserId(workoutId).stream()
                .map(drillEntity -> modelMapper.map(drillEntity, Drill.class))
                .collect(Collectors.toList());
    }

    public Drill createDrill(Drill drill) {
        DrillEntity drillEntity = modelMapper.map(drill, DrillEntity.class);
        drillEntity.setDrillId(DrillUtils.createUniqueDrillId());
        drillEntity.setShotsRequired(new ShotsTaken());
        drillEntity.setShotsToBeTaken(new ShotsTaken());
        drillEntity.setShotsMade(new ShotsTaken());
        updateBaseDefaultFields(drillEntity);
        return modelMapper.map(drillRepository.save(drillEntity), Drill.class);
    }

    public List<Drill> createDrillFromDrillCreationRequest(DrillCreationRequest drillCreationRequest) {
        List<DrillEntity> drillEntityList = new ArrayList<>();
        drillCreationRequest.getCustomWorkoutDrills().forEach(customWorkoutDrill -> {
            DrillEntity build = DrillEntity.builder()
                    .drillId(DrillUtils.createUniqueDrillId())
                    .userId(drillCreationRequest.getUserId())
                    .workoutId(drillCreationRequest.getWorkoutId())
                    .drillType(customWorkoutDrill.getDrillType())
                    .drillName(customWorkoutDrill.getDrillName())
                    .drillDifficulty(customWorkoutDrill.getDrillDifficulty())
                    .categories(customWorkoutDrill.getCategories())
                    .tags(customWorkoutDrill.getTags())
                    .description(customWorkoutDrill.getDescription())
                    .shotsRequired(new ShotsTaken())
                    .shotsToBeTaken(new ShotsTaken())
                    .shotsMade(new ShotsTaken())
                    .build();
            updateBaseDefaultFields(build);
            drillEntityList.add(build);

        });
        drillRepository.saveAll(drillEntityList);
        return drillEntityList.stream()
                .map(drillEntity -> modelMapper.map(drillEntity, Drill.class))
                .collect(Collectors.toList());
    }

    public DrillEntity allZonesExceptLayups(ShotsTaken shotsRequired, String drillId, Integer numberOfShots) {
        DrillEntity drillEntity = drillRepository.findById(drillId).orElseThrow(() ->
                new DrillNotFoundException("Drill not found for ID: " + drillId));
        drillEntity.setShotsRequired(shotsRequired);
        drillEntity.setShotsToBeTaken(checkAndSetShots(shotsRequired, numberOfShots));
        updateBaseDefaultFields(drillEntity);
        return drillRepository.save(drillEntity);
    }

    public Drill updateDrill(Drill drill) {
        DrillEntity drillEntity = drillRepository.findById(drill.getDrillId()).orElseThrow(() ->
                new DrillNotFoundException("Drill not found for ID: " + drill.getDrillId()));
        updateBaseDefaultFields(drillEntity);
        return modelMapper.map(drillRepository.save(drillEntity), Drill.class);
    }

    public Boolean deleteDrill(String id) {
        drillRepository.delete(drillRepository.findById(id).orElseThrow(() ->
                new DrillNotFoundException("Drill not found for ID: " + id)));
        return true;
    }

    public void processDrillCreationRequest(DrillCreationRequest drillCreationRequest) {
        // Implement your business logic to handle the drill creation request
        System.out.println("Processing Drill Creation Request for Workout ID: " + drillCreationRequest.getWorkoutId());
        // Process each drill type in the request
        for (CustomWorkoutDrill customWorkoutDrill : drillCreationRequest.getCustomWorkoutDrills()) {
            System.out.println("Processing Drill Type: " + customWorkoutDrill);
        }
        // You can add your own logic here to handle the request
    }

    private static void updateBaseDefaultFields(DrillEntity drillEntity) {
        String userName = DrillUtils.getUserName();
        String time = DrillUtils.getCurrentSqlTime().toString();
        drillEntity.setLastUpdatedBy(userName);
        drillEntity.setLastUpdatedDate(time);
        if (drillEntity.getCreationDate() != null) {
            drillEntity.setCreatedBy(userName);
            drillEntity.setCreationDate(time);
        }
    }

    private ShotsTaken checkAndSetShots(ShotsTaken shotsTaken, Integer numberOfShots) {
        if (shotsTaken.getThreePointLeftCorner() == 1) {
            shotsTaken.setThreePointLeftCorner(numberOfShots);
        }
        if (shotsTaken.getThreePointRightCorner() == 1) {
            shotsTaken.setThreePointRightCorner(numberOfShots);
        }
        if (shotsTaken.getThreePointLeftWing() == 1) {
            shotsTaken.setThreePointLeftWing(numberOfShots);
        }
        if (shotsTaken.getThreePointRightWing() == 1) {
            shotsTaken.setThreePointRightWing(numberOfShots);
        }
        if (shotsTaken.getThreePointMiddle() == 1) {
            shotsTaken.setThreePointMiddle(numberOfShots);
        }
        if (shotsTaken.getTwoPointLeftCorner() == 1) {
            shotsTaken.setTwoPointLeftCorner(numberOfShots);
        }
        if (shotsTaken.getTwoPointRightCorner() == 1) {
            shotsTaken.setTwoPointRightCorner(numberOfShots);
        }
        if (shotsTaken.getTwoPointLeftWing() == 1) {
            shotsTaken.setTwoPointLeftWing(numberOfShots);
        }
        if (shotsTaken.getTwoPointRightWing() == 1) {
            shotsTaken.setTwoPointRightWing(numberOfShots);
        }
        if (shotsTaken.getTwoPointMiddle() == 1) {
            shotsTaken.setTwoPointMiddle(numberOfShots);
        }
        return shotsTaken;
    }
}
