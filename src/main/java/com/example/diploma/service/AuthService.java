package com.example.diploma.service;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.Role;

/**
 * @author Anatoliy Shikin
 */

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterReq registerReq, Role role);

    boolean setPassword(NewPassword newPassword, String name);
}
