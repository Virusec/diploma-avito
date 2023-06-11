package com.example.diploma.service;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author anna
 */
public interface AdService {
    Ads add(CreateAds properties, MultipartFile image, String email);

    FullAds getFullAdsById(int id);

    ResponseWrapperAds getAllAds();

    ResponseWrapperAds getAllMyAds(String name);

    void deleteAd(int id);

    Ads updateAds(int id, CreateAds ads);
}
