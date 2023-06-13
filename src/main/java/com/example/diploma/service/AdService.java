package com.example.diploma.service;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.entity.AdEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anna
 */
public interface AdService {
    Ads add(CreateAds properties, MultipartFile image, String email);

    FullAds getFullAdsById(int id);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAllMyAds(String name);

    void delete(int id);

    Ads update(int id, CreateAds ads);

    AdEntity getEntity(int id);
}
