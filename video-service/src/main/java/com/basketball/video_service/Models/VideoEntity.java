package com.basketball.video_service.Models;

import com.basketball.codegen_service.codegen.types.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "VIDEOS")
public class VideoEntity extends DatabaseDefaultFields {

    @Id
    String videoId;

    @Enumerated(EnumType.STRING)
    DrillType drillType;

    String videoUrl;
}
