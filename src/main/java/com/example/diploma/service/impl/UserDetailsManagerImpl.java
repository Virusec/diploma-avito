package com.example.diploma.service.impl;

import com.example.diploma.dto.UserSecurity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */

@RequiredArgsConstructor
@Component
public class UserDetailsManagerImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserSecurity(repository.findByEmail(username)
                .orElseThrow(() -> new FindNoEntityException("пользователь")));
    }
}
