package com.basketball.auth_service.controller;

import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.auth_service.dto.AuthenticationResponse;
import com.basketball.auth_service.service.AuthService;
import com.basketball.codegen_service.codegen.types.LoginInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginInput request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserAuthEntity request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return ResponseEntity.ok(authService.refreshToken(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String token, @RequestParam boolean isRefresh) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        if (authService.validateToken(token, isRefresh)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        authService.logout(token);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}