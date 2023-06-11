package com.example.diploma.service.impl;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.mapping.AdMapper;
import com.example.diploma.repository.AdRepository;
import com.example.diploma.service.AdService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final AdMapper mapper;

    @Override
    public Ads add(CreateAds properties, MultipartFile image, String email) {
        AdEntity ad = mapper.createAdsToEntity(properties, userService.getEntity(email));
        return mapper.entityToAdsDto(adRepository.save(ad));
    }

    @Override
    public FullAds getFullAdsById(int id) {
        return mapper.entityToFullAdsDto(adRepository.findById(id).get());
    }

    @Override
    public void delete(int id) {
        adRepository.deleteById(id);
    }

    @Override
    public Ads update(int id, CreateAds ads) {
        AdEntity entity = adRepository.findById(id).orElseThrow(RuntimeException::new);
        entity.setTitle(ads.getTitle());
        entity.setDescription(ads.getDescription());
        entity.setPrice(ads.getPrice());
        adRepository.save(entity);
        return mapper.entityToAdsDto(entity);
    }

    @Override
    public ResponseWrapperAds getAllAds() {
        return getWrapper(adRepository.findAll());
    }

    @Override
    public ResponseWrapperAds getAllMyAds(String name) {
        return getWrapper(adRepository.findAllByAuthorEmail(name));
    }

    private ResponseWrapperAds getWrapper(List<AdEntity> list) {
        List<Ads> result = new LinkedList<>();
        list.forEach((entity -> result.add(mapper.entityToAdsDto(entity))));
        return new ResponseWrapperAds(result.size(), result);
    }
}
