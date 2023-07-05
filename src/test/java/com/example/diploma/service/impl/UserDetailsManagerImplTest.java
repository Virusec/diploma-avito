package com.example.diploma.service.impl;

import com.example.diploma.dto.Role;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author anna
 */

@ExtendWith(MockitoExtension.class)
class UserDetailsManagerImplTest {
    @InjectMocks
    private UserDetailsManagerImpl manager;
    @Mock
    private UserRepository repository;
    private final String userName = "aaa@ug.ru";
    private final String password = "123456789";
    private final Role role = Role.USER;

    @Test
    void changePasswordTest() {
        when(repository.findByEmail(userName)).thenReturn(Optional.of(getEntity()));
        UserEntity entity = getEntity();
        String renewedPassword = "renewedPassword";
        entity.setPassword(renewedPassword);
        manager.changePassword(renewedPassword, userName);
        verify(repository).save(entity);
    }

    @Test
    void userExistsTrue() {
        when(repository.findByEmail(userName)).thenReturn(Optional.of(getEntity()));
        Assertions.assertTrue(manager.userExists(userName));
    }

    @Test
    void userExistsFalse() {
        when(repository.findByEmail(userName)).thenReturn(Optional.empty());
        Assertions.assertFalse(manager.userExists(userName));
    }

    @Test
    void loadUserByUsernameIfExists() {
        when(repository.findByEmail(userName)).thenReturn(Optional.of(getEntity()));
        UserDetails user = manager.loadUserByUsername(userName);
        assert user.getPassword().equals(password);
        assert user.getUsername().equals(userName);
        assert user.getAuthorities().equals(role.getAuthorities());
    }

    @Test
    void loadUserByUsernameIfNotExists() {
        when(repository.findByEmail(userName)).thenReturn(Optional.empty());
        Assertions.assertThrows(FindNoEntityException.class, () -> manager.loadUserByUsername(userName));
    }

    @Test
    void createUserTest() {
        UserEntity entity = getEntity();
        manager.createUser(entity);
        verify(repository).save(entity);
    }

    private UserEntity getEntity() {
        int id = 1;
        return new UserEntity(id, password, userName, null, null, null, role, null);
    }
}