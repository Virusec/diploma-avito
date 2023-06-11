package com.example.diploma.controller;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.service.AdService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author anna
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")

public class AdController {
    private final AdService adService;

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ads> addAd(@RequestPart CreateAds properties, @RequestPart MultipartFile image,
                                     Authentication authentication) {
        return ResponseEntity.ok(adService.add(properties, image, authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAds> getAds(@PathVariable int id) {
        return ResponseEntity.ok(adService.getFullAdsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {
        adService.deleteAd(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable int id, @RequestBody CreateAds ads) {
        return ResponseEntity.ok(adService.updateAds(id, ads));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAllMyAds(authentication.getName()));
    }

    //TODO: доделать
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}