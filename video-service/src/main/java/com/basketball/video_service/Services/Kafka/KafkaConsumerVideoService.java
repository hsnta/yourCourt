package com.basketball.video_service.Services.Kafka;

import com.basketball.video_service.Services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerVideoService {

    @Autowired
    MessageProcessorVideoService messageProcessorVideoService;
    @Autowired
    VideoService videoService;

    @KafkaListener(topics = "user_service_topic", groupId = "group_id")
    public void consumeUserService(String message) {
        messageProcessorVideoService.getExampleWorkoutData(message);
    }

    @KafkaListener(topics = "user_performance_service_topic", groupId = "group_id")
    public void consumeUserPerformanceService(String message) {
        messageProcessorVideoService.getExampleWorkoutData(message);
    }
}