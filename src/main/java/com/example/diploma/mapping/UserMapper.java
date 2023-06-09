package com.example.diploma.mapping;


import com.example.diploma.dto.RegisterReq;
import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author anna
 */
@Component
public class UserMapper {
    public User entityToUserDto(UserEntity entity) {
        return new User(entity.getId(), entity.getEmail(), entity.getFirstName(),
                entity.getLastName(), entity.getPhone(), entity.getImagePath());
    }
//из Authentication берем name, по нему ищем UserEntity и передаем сюда
    public UserEntity userDtoToEntity(User user, UserEntity entity) {
        entity.setPhone(user.getPhone());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        return entity;
    }

    public UserEntity registerReqDtoToEntity(RegisterReq req) {
        return new UserEntity(req.getPassword(), req.getUsername(), req.getFirstName(),
                req.getLastName(), req.getPhone(), req.getRole());
    }
}
