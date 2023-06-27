package com.example.diploma.service.impl;

import com.example.diploma.dto.User;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.repository.UserRepository;
import com.example.diploma.service.ImageService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public User update(User user, String name) {
        log.info("Изменение данных профиля пользователя " + name);
        return mapper.entityToUserDto(userRepository.save(mapper.userDtoToEntity(user, getEntity(name))));
    }

    @Override
    public void delete(String name) {
        userRepository.deleteByEmail(name);
        log.info("Удаление пользователя " + name);
    }

    @Override
    public User get(String name) {
        return mapper.entityToUserDto(getEntity(name));
    }

    @Override
    public UserEntity getEntity(String name) {
        return userRepository.findByEmail(name).orElseThrow(() -> new FindNoEntityException("пользователь"));
    }

    @Override
    public void uploadImage(MultipartFile image, String name) throws IOException {
        UserEntity userEntity = getEntity(name);
        ImageEntity imageEntity = userEntity.getImage();
        userEntity.setImage(imageService.saveImage(image));
        userRepository.save(userEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
    }
}
