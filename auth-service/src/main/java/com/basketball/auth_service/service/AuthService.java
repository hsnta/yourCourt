package com.basketball.auth_service.service;

import com.basketball.auth_service.repository.UserAuthEntityRepository;
import com.basketball.codegen_service.codegen.types.LoginInput;
import com.basketball.codegen_service.codegen.types.User;
import domain.UserAuthEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserAuthEntityRepository repo;

    public String login(LoginInput request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }
        throw new RuntimeException("Wrong credentials");
    }

    public UserAuthEntity register(UserAuthEntity request) {
        if (repo.existsById(request.getUsername())) {
            throw new RuntimeException("");
        }
        repo.save(request);

    }
}