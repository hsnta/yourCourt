package com.basketball.video_service.Services;

import com.basketball.codegen_service.codegen.types.*;
import com.basketball.video_service.Models.VideoEntity;
import com.basketball.video_service.Utils.VideoUtils;
import com.basketball.video_service.Exceptions.VideoNotFoundException;

import com.basketball.video_service.Repositories.VideoRepository;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    ModelMapper modelMapper;

    public Video getVideoByDrillType(DrillType drillType) {
        return modelMapper.map(videoRepository.findByDrillType(drillType).orElseThrow(), Video.class);
    }

    public Video createVideo(Video video) {
        VideoEntity videoEntity = modelMapper.map(video, VideoEntity.class);
        videoEntity.setVideoId(VideoUtils.createUniqueVideoId());
        updateBaseDefaultFields(videoEntity);
        return modelMapper.map(videoRepository.save(videoEntity), Video.class);
    }

    public Video updateVideo(Video video) {
        VideoEntity videoEntity = videoRepository.findById(video.getVideoId()).orElseThrow(() ->
                new VideoNotFoundException("Drill not found for ID: " + video.getVideoId()));
        updateBaseDefaultFields(videoEntity);
        return modelMapper.map(videoRepository.save(videoEntity), Video.class);
    }

    public Boolean deleteVideo(String id) {
        videoRepository.delete(videoRepository.findById(id).orElseThrow(() ->
                        new VideoNotFoundException("Drill not found for ID: " + id)));
        return true;
    }

    private static void updateBaseDefaultFields(VideoEntity videoEntity) {
        String userName = VideoUtils.getUserName();
        String time = VideoUtils.getCurrentSqlTime().toString();
        videoEntity.setLastUpdatedBy(userName);
        videoEntity.setLastUpdatedDate(time);
        if (videoEntity.getCreationDate() != null) {
            videoEntity.setCreatedBy(userName);
            videoEntity.setCreationDate(time);
        }
    }
}
