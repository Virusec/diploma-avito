package com.example.diploma.service.impl;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.mapping.AdMapper;
import com.example.diploma.repository.AdRepository;
import com.example.diploma.service.AdService;
import com.example.diploma.service.ImageService;
import com.example.diploma.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final ImageService imageService;
    private final AdMapper mapper;

    @Override
    public Ads add(CreateAds properties, MultipartFile image, String email) throws IOException {
        AdEntity ad = mapper.createAdsToEntity(properties, userService.getEntity(email));
        ad.setImage(imageService.saveImage(image));
        log.info("Добавление нового объявления");
        return mapper.entityToAdsDto(adRepository.save(ad));
    }

    @Override
    public FullAds getFullAdsById(int id) {
        return mapper.entityToFullAdsDto(getEntity(id));
    }

    @Override
    public void delete(int id) {
        adRepository.deleteById(id);
        log.info("Удаление объявления с id " + id);
    }

    @Override
    public Ads update(int id, CreateAds ads) {
        AdEntity entity = getEntity(id);
        entity.setTitle(ads.getTitle());
        entity.setDescription(ads.getDescription());
        entity.setPrice(ads.getPrice());
        adRepository.save(entity);
        log.info("Редактирование объявления с id " + id);
        return mapper.entityToAdsDto(entity);
    }

    @Override
    public AdEntity getEntity(int id) {
        return adRepository.findById(id).orElseThrow(() -> new FindNoEntityException("объявление"));
    }

    @Override
    public void uploadImage(int id, MultipartFile image) throws IOException {
        AdEntity adEntity = getEntity(id);
        ImageEntity imageEntity = adEntity.getImage();
        adEntity.setImage(imageService.saveImage(image));
        adRepository.save(adEntity);
        if (imageEntity != null) {
            imageService.deleteImage(imageEntity);
        }
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
