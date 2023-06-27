package com.example.diploma.mapping;

import com.example.diploma.dto.Role;
import com.example.diploma.dto.User;
import com.example.diploma.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserMapper();
    }

    @Test
    void entityToUserDtoTest() {
        UserEntity entity = new UserEntity(1, "123456", "xxx@mail.ru",
                "Oleg", "Olegov", "+78008889922", Role.USER);
        User user = mapper.entityToUserDto(entity);
        assert user.getId() == entity.getId();
        assert user.getEmail().equals(entity.getEmail());
        assert user.getFirstName().equals(entity.getFirstName());
        assert user.getLastName().equals(entity.getLastName());


    }

    @Test
    void userDtoToEntity() {
    }

    @Test
    void registerReqDtoToEntity() {
    }
}