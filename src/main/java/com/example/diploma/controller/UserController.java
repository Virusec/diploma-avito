package com.example.diploma.controller;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anna
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok(new User());
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new User());
    }

    @PutMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}
