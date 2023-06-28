package com.example.diploma.service;

import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anna
 */
public interface UserService {
    User update(User user, String name);

    void delete(String name);

    User get(String name);

    UserEntity getEntity(String name);

    void uploadImage(MultipartFile image, String name) throws IOException;

    UserEntity getEntityById(int id);
}

