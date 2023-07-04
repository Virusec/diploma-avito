package com.example.diploma.service.impl;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.Role;
import com.example.diploma.dto.UserSecurity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.mapping.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author anna
 */

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserDetailsManagerImpl manager;
    @Mock
    private UserMapper mapper;
    @Spy
    private BCryptPasswordEncoder encoder;
    private final String userName = "aaa@ug.ru";
    private final String password = "123456789";
    private final String renewedPassword = "renewedPassword";
    private final String fName = "Oleg";
    private final String lName = "Olegov";
    private final String phone = "+78008889922";
    private final Role role = Role.USER;


    @Test
    void loginTrue() {
        UserSecurity userSecurity = getUserSecurity();
        when(manager.userExists(userName)).thenReturn(true);
        when(manager.loadUserByUsername(userName)).thenReturn(userSecurity);
        Assertions.assertTrue(authService.login(userName, password));
    }

    @Test
    void loginIfUserNotExists() {
        when(manager.userExists(userName)).thenReturn(false);
        Assertions.assertFalse(authService.login(userName, password));
    }

    @Test
    void loginIfInvalidPassword() {
        UserSecurity userSecurity = getUserSecurity();
        when(manager.userExists(userName)).thenReturn(true);
        when(manager.loadUserByUsername(userName)).thenReturn(userSecurity);
        when(encoder.matches(password, userSecurity.getPassword())).thenReturn(false);
        Assertions.assertFalse(authService.login(userName, password));
    }


    @Test
    void registerIfAlreadyExists() {
        RegisterReq req = getRegisterReq();
        when(manager.userExists(userName)).thenReturn(true);
        Assertions.assertFalse(authService.register(req, role));
    }

    @Test
    void registerSuccessfully() {
        RegisterReq req = getRegisterReq();
        UserEntity entity = getEntity();
        when(manager.userExists(userName)).thenReturn(false);
        when(mapper.registerReqDtoToEntity(req)).thenReturn(entity);
        Assertions.assertTrue(authService.register(req, role));
        verify(manager).createUser(entity);
    }


    @Test
    void setPasswordSuccessfully() {
        NewPassword newPassword = new NewPassword();
        String newEncodePass = "123456";
        newPassword.setCurrentPassword(password);
        newPassword.setNewPassword(renewedPassword);
        UserSecurity user = getUserSecurity();
        when(manager.loadUserByUsername(userName)).thenReturn(user);
        when(encoder.encode(renewedPassword)).thenReturn(newEncodePass);
        Assertions.assertTrue(authService.setPassword(newPassword, userName));
        verify(manager).changePassword(newEncodePass);
    }

    @Test
    void setPasswordNoMatchingCurrentPassword() {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("pass");
        newPassword.setNewPassword(renewedPassword);
        UserSecurity user = getUserSecurity();
        when(manager.loadUserByUsername(userName)).thenReturn(user);
        Assertions.assertFalse(authService.setPassword(newPassword, userName));
    }

    private UserSecurity getUserSecurity() {
        return new UserSecurity(getEntity());
    }

    private RegisterReq getRegisterReq() {
        RegisterReq req = new RegisterReq();
        req.setPhone(phone);
        req.setUsername(userName);
        req.setPassword(password);
        req.setLastName(lName);
        req.setFirstName(fName);
        return req;
    }

    private UserEntity getEntity() {
        int id = 1;
        return new UserEntity(id, encoder.encode(password), userName, fName, lName, phone, role, null);
    }
}