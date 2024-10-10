package com.basketball.video_service.Controllers;

import com.basketball.codegen_service.codegen.types.DrillType;
import com.basketball.video_service.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
public class TemporaryLinkController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/generate-link/{drillType}")
    public ResponseEntity<String> generateTemporaryLink(@PathVariable("drillType") DrillType drillType) {
        // Generate token valid for 1 hour (3600 * 1000 milliseconds)
        String token = tokenService.generateTemporaryLink(drillType.toString(), 3600 * 500);

        // Build a temporary link (you can include your domain and port here)
        String temporaryLink = "http://localhost:8766/api/videos/" + drillType + "?token=" + token;

        // Send the link to the UI
        return ResponseEntity.ok(temporaryLink);
    }
}
