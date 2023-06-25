package com.example.diploma.service.impl;

import com.example.diploma.dto.UserSecurity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDetailsManagerImpl implements UserDetailsManager {

    private final UserRepository repository;

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = (UserEntity) loadUserByUsername(currentUser.getName());
        entity.setPassword(newPassword);
        repository.save(entity);
    }

    @Override
    public boolean userExists(String username) {
        return repository.findByEmail(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserSecurity(repository.findByEmail(username)
                .orElseThrow(() -> new FindNoEntityException("пользователь")));
    }

    @Override
    public void createUser(UserDetails user) {
        log.info("Регистрация нового пользователя");
        repository.save(((UserSecurity)user).getEntity());
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }
}
