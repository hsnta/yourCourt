package com.basketball.video_service.Repositories;


import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.video_service.Models.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface VideoRepository extends MongoRepository<VideoEntity, String> {

}
