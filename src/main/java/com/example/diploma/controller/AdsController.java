package com.example.diploma.controller;

import com.example.diploma.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author anna
 */
@RestController
@RequestMapping("/ads")

public class AdsController {
    @GetMapping
    public ResponseEntity<?> getAllAds() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAd(@RequestBody CreateAds properties, @RequestBody MultipartFile image) {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAds(@PathVariable int id) {
        return ResponseEntity.ok(new FullAds());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAds(@PathVariable int id, @RequestBody CreateAds ads) {
        return ResponseEntity.ok(new Ads());
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAdsMe() {
        return ResponseEntity.ok(new ResponseWrapperAds());
    }
    @PutMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}