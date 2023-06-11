package com.example.diploma.controller;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anna
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new User());
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}
