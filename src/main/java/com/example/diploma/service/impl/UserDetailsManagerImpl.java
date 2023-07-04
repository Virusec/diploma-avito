package com.example.diploma.service.impl;

import com.example.diploma.dto.UserSecurity;
import com.example.diploma.entity.UserEntity;
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

    public void changePassword(String newPassword, String name) {
        UserEntity entity = getEntity(name);
        entity.setPassword(newPassword);
        repository.save(entity);
    }

    public boolean userExists(String username) {
        return repository.findByEmail(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserSecurity(getEntity(username));
    }

    public UserEntity getEntity(String username) {
        return repository.findByEmail(username)
                .orElseThrow(() -> new FindNoEntityException("пользователь"));
    }

    public void createUser(UserEntity user) {
        repository.save(user);
    }
}
