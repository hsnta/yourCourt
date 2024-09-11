package com.basketball.auth_service.service;

import com.basketball.auth_service.domain.UserAuthEntity;
import com.basketball.auth_service.dto.UserDetailsImpl;
import com.basketball.auth_service.repository.UserAuthEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserAuthEntityRepository userAuthEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthEntity user = userAuthEntityRepository.findByUsername(username).orElseThrow();
        return new UserDetailsImpl(user);
    }
}