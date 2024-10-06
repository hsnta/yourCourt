package com.basketball.video_service.Controllers;

import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.video_service.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/videos")
public class VideoController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/{drillType}")
    public ResponseEntity<byte[]> getVideoByDrillType(@PathVariable("drillType") DrillType drillType, @RequestParam("token") String token) {
        if (!tokenService.isTokenValid(token)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // Token is invalid or expired
        }
        try {
            String fileName = drillType + ".mp4"; // Assuming the files are .mp4
            Resource resource = new ClassPathResource("static/" + fileName);

            // Check if the file exists before proceeding
            if (!resource.exists()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Convert file to byte array
            Path path = Paths.get(resource.getURI());
            byte[] fileContent = Files.readAllBytes(path);

            // Set the HTTP response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
