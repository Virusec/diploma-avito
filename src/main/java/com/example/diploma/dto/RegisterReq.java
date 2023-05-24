package com.example.diploma.dto;

import lombok.Data;

/**
 * @author Anatoliy Shikin
 */
@Data
public class RegisterReq {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
