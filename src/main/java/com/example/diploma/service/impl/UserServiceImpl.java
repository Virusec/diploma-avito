package com.example.diploma.service.impl;

import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.repository.UserRepository;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    //TODO: ImageService
    private final UserMapper userMapper;

    @Override
    public User add(RegisterReq req) {
        return userMapper.entityToUserDto(userRepository.save(userMapper.registerReqDtoToEntity(req)));
    }

    @Override
    public User update(User user, String name) {
        UserEntity entity = userRepository.findByEmail(name);
        return userMapper.entityToUserDto(userRepository.save(userMapper.userDtoToEntity(user, entity)));
    }

    @Override
    public void delete(String name) {
        userRepository.deleteByEmail(name);
    }

    @Override
    public User get(String name) {
        return userMapper.entityToUserDto(getEntity(name));
    }

    @Override
    public UserEntity getEntity(String name) {
        return userRepository.findByEmail(name);
    }
}
