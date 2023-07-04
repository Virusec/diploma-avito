package com.example.diploma.service;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.entity.AdEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anna
 */
public interface AdService {
    Ads add(CreateAds properties, MultipartFile image, String email) throws IOException;

    FullAds getFullAdsById(int id);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAllMyAds(String name);

    void delete(int id) throws IOException;

    Ads update(int id, CreateAds ads);

    AdEntity getEntity(int id);

    void uploadImage(int id, MultipartFile image) throws IOException;
}
