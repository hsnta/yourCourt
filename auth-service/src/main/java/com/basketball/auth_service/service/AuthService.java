package com.basketball.auth_service.service;

import com.basketball.auth_service.client.UserServiceClient;
import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.auth_service.dto.AuthenticationResponse;
import com.basketball.auth_service.repository.UserAuthEntityRepository;
import com.basketball.codegen_service.codegen.types.LoginInput;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            return AuthenticationResponse.builder()
                    .accessToken(jwtService.generateToken(request.getUsername(), false))
                    .refreshToken(jwtService.generateToken(request.getUsername(), true))
                    .build();
        }
        throw new RuntimeException("Wrong credentials");
    }

    @Transactional
    public AuthenticationResponse register(UserAuthEntity request) {
        if (repo.existsById(request.getUsername())) {
            throw new RuntimeException("");
        }
        String nonEncodedPassword = request.getPassword();
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        repo.save(request);
        AuthenticationResponse tokens = login(LoginInput.newBuilder()
                .username(request.getUsername())
                .password(nonEncodedPassword)
                .build());
        userServiceClient.saveNewUser(request, tokens.getAccessToken());
        return tokens;
    }

    public AuthenticationResponse refreshToken() {
        return null;
    }

    public void logout() {
        // invalidate auth token
        // invalidate refresh token
    }
}