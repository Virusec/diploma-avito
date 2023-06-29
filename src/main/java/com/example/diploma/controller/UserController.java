package com.example.diploma.controller;

import com.example.diploma.dto.NewPassword;
import com.example.diploma.dto.User;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.service.AuthService;
import com.example.diploma.service.ImageService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    private final AuthService authService;
    private final ImageService imageService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                         Authentication authentication) {
        if (authService.setPassword(newPassword, authentication.getName())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        return ResponseEntity.ok( userService.get(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody User newUser, Authentication authentication) {
        return ResponseEntity.ok(userService.update(newUser, authentication.getName()));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image, Authentication auth) throws IOException {
        userService.uploadImage(image, auth.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) throws IOException {
        long imageId = userService.getEntityById(id).getImage().getId();
        ImageEntity image = imageService.getEntity(imageId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(image.getMediaType()));
        headers.setContentLength(image.getFileSize());
        return ResponseEntity.ok().headers(headers).body(imageService.getImage(imageId));
    }
}
