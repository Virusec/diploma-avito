package com.example.diploma.entity;

import com.example.diploma.dto.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author anna
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String password, String email, String firstName, String lastName, String phone, Role role) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    //TODO: переделать получение пути файла, когда будем делать хранение картинок
    public String getImagePath() {
        return null;
    }

}
