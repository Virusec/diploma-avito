package com.example.diploma.service.impl;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.Role;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Anatoliy Shikin
 */

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public boolean login(String userName, String password) {
        if (!((UserDetailsManagerImpl) manager).userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (((UserDetailsManagerImpl) manager).userExists(registerReq.getUsername())) {
            return false;
        }
        registerReq.setRole(role);
        registerReq.setPassword(encoder.encode(registerReq.getPassword()));
        ((UserDetailsManagerImpl) manager).createUser(mapper.registerReqDtoToEntity(registerReq));
        return true;
    }

    @Override
    public boolean setPassword(NewPassword newPassword, String name) {
        if (encoder.matches(newPassword.getCurrentPassword(), manager.loadUserByUsername(name).getPassword())) {
            ((UserDetailsManagerImpl) manager).changePassword(encoder.encode(newPassword.getNewPassword()));
            return true;
        }
        return false;
    }
}
