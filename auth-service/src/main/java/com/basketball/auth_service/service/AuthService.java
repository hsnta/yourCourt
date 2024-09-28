package com.basketball.auth_service.service;

import com.basketball.auth_service.client.UserServiceClient;
import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.auth_service.dto.AuthenticationResponse;
import com.basketball.auth_service.repository.UserAuthEntityRepository;
import com.basketball.codegen_service.codegen.types.LoginInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAuthEntityRepository repo;
    private final UserServiceClient userServiceClient;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationResponse login(LoginInput request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated()) {
            int logoutCounter = repo.getLogoutCounterByUserName(request.getUsername()).orElseThrow();
            return AuthenticationResponse.builder()
                    .accessToken(jwtService.generateToken(Map.of("ctr", logoutCounter), request.getUsername(), false))
                    .refreshToken(jwtService.generateToken(Map.of("ctr", logoutCounter), request.getUsername(), true))
                    .build();
        }
        throw new RuntimeException("Wrong credentials");
    }

    @Transactional
    public AuthenticationResponse register(UserAuthEntity request) {
        if (repo.existsById(request.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        String nonEncodedPassword = request.getPassword();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setLogoutCounter(0);
        repo.save(request);
        AuthenticationResponse tokens = login(LoginInput.newBuilder()
                .username(request.getUsername())
                .password(nonEncodedPassword)
                .build());
        userServiceClient.saveNewUser(request, tokens.getAccessToken());
        return tokens;
    }

    public AuthenticationResponse refreshToken(String token) {
        String userName = jwtService.extractUsername(token, true);
        int logoutCounter = repo.getLogoutCounterByUserName(userName).orElseThrow();
        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateToken(Map.of("ctr", logoutCounter), userName, false))
                .refreshToken(jwtService.generateToken(Map.of("ctr", logoutCounter), userName, true))
                .build();
    }

    @Transactional
    public void logout(String token) {
        // invalidate auth token
        // invalidate refresh token
        String userName = jwtService.extractUsername(token, false);
        UserAuthEntity user = repo.findByUsername(userName).orElseThrow();
        user.setLogoutCounter(user.getLogoutCounter() + 1);
        repo.save(user);
    }

    public boolean validateToken(String token, boolean isRefresh) {
        String userName = jwtService.extractUsername(token, isRefresh);
        int logoutCounter = repo.getLogoutCounterByUserName(userName).orElseThrow();
        return jwtService.isTokenValid(token, isRefresh, logoutCounter);

    }
}