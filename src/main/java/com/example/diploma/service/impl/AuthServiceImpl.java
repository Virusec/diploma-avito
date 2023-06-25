package com.example.diploma.service.impl;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.Role;
import com.example.diploma.dto.UserSecurity;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * @author Anatoliy Shikin
 */

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (manager.userExists(registerReq.getUsername())) {
            return false;
        }
        registerReq.setRole(role);
        registerReq.setPassword(encoder.encode(registerReq.getPassword()));
        manager.createUser(new UserSecurity(mapper.registerReqDtoToEntity(registerReq)));
        return true;
    }

    @Override
    public boolean setPassword(NewPassword newPassword, String name) {
        if (manager.userExists(name) && encoder.matches(newPassword.getCurrentPassword(),
                manager.loadUserByUsername(name).getPassword())) {
            manager.changePassword(newPassword.getCurrentPassword(), encoder.encode(newPassword.getNewPassword()));
            return true;
        }
        return false;
    }
}
