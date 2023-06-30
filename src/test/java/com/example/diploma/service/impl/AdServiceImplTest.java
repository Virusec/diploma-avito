package com.example.diploma.service.impl;

import com.example.diploma.dto.Ads;
import com.example.diploma.dto.CreateAds;
import com.example.diploma.dto.FullAds;
import com.example.diploma.dto.ResponseWrapperAds;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.mapping.AdMapper;
import com.example.diploma.repository.AdRepository;
import com.example.diploma.service.ImageService;
import com.example.diploma.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {
    @InjectMocks
    private AdServiceImpl adService;
    @Mock
    private AdRepository adRepository;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;
    @Mock
    private AdMapper mapper;
    @Mock
    private UserEntity user;
    private final int price = 100;
    private final int pk = 1;
    private final int id = 10;
    private final String title = "Little kitty";
    private final String description = "Pretty white kitty";
    private final String email = "xxx@mail.ru";
    private final String imagePath = "ads/image/" + pk;
    private AdEntity adEntity;

    @BeforeEach
    void setUp() {
        adEntity = new AdEntity(pk, user, title, price, description, null);
    }

    @Test
    void addTest() throws IOException {
        byte[] inputArray = "Test".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("fileName", inputArray);
        CreateAds properties = new CreateAds();
        properties.setTitle(title);
        properties.setPrice(price);
        properties.setDescription(description);
        when(userService.getEntity(email)).thenReturn(user);
        when(mapper.createAdsToEntity(properties, user)).thenReturn(adEntity);
        when(adRepository.save(adEntity)).thenReturn(adEntity);
        when(mapper.entityToAdsDto(adEntity)).thenReturn(new Ads(id, imagePath, pk, price, title));

        Ads ad = adService.add(properties, mockMultipartFile, email);
        verify(imageService).saveImage(mockMultipartFile);
        assert ad.getAuthor() == id;
        assert ad.getTitle().equals(title);
        assert ad.getPk() == pk;
        assert ad.getPrice() == price;
        assert ad.getImage().equals("ads/image/" + pk);
    }

    @Test
    void getFullAdsByIdTest() {
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));
        String fName = "Oleg";
        String lName = "Olegov";
        String phone = "+78008889922";
        when(mapper.entityToFullAdsDto(adEntity)).thenReturn(new FullAds(pk, fName, lName, description,
                email, imagePath, phone, price, title));

        FullAds fullAds = adService.getFullAdsById(pk);
        assert fullAds.getPk() == pk;
        assert fullAds.getAuthorFirstName().equals(fName);
        assert fullAds.getAuthorLastName().equals(lName);
        assert fullAds.getDescription().equals(description);
        assert fullAds.getEmail().equals(email);
        assert fullAds.getImage().equals(imagePath);
        assert fullAds.getPhone().equals(phone);
        assert fullAds.getPrice() == price;
        assert fullAds.getTitle().equals(title);
    }

    @Test
    void deleteTest() {
        adService.delete(pk);
        verify(adRepository).deleteById(pk);
    }

    @Test
    void updateTest() {
        String newDescription = "newDesc";
        String newTitle = "newTitle";
        int newPrice = 200;
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));
        CreateAds ads = new CreateAds();
        ads.setDescription(newDescription);
        ads.setTitle(newTitle);
        ads.setPrice(newPrice);
        AdEntity newEntity = new AdEntity(pk, user, newTitle, newPrice, newDescription, null);
        when(mapper.entityToAdsDto(newEntity)).thenReturn(new Ads(id, imagePath, pk, newPrice, newTitle));

        Ads ad = adService.update(pk, ads);
        verify(adRepository).save(newEntity);
        assert ad.getTitle().equals(newTitle);
        assert ad.getPrice() == newPrice;
    }

    @Test
    void uploadImageTest() throws IOException {
        byte[] inputArray = "Test".getBytes();
        MockMultipartFile mockMultipartFile = new MockMultipartFile("fileName", inputArray);
        long size = 1000L;
        String mediaType = "mediaType";
        ImageEntity imageEntity = new ImageEntity(id, size, mediaType);
        adEntity.setImage(imageEntity);
        when(adRepository.findById(pk)).thenReturn(Optional.of(adEntity));

        adService.uploadImage(pk, mockMultipartFile);
        verify(imageService).saveImage(mockMultipartFile);
        verify(imageService).deleteImage(imageEntity);

    }

    @Test
    void getAllAdsTest() {
        AdEntity adEntity1 = new AdEntity();
        AdEntity adEntity2 = new AdEntity();
        Ads ad = new Ads(id, imagePath, pk, price, title);
        when(adRepository.findAll()).thenReturn(List.of(adEntity1, adEntity, adEntity2));
        when(mapper.entityToAdsDto(adEntity)).thenReturn(ad);
        when(mapper.entityToAdsDto(adEntity1)).thenReturn(new Ads());
        when(mapper.entityToAdsDto(adEntity2)).thenReturn(new Ads());

        ResponseWrapperAds wrapperAds = adService.getAllAds();
        assert wrapperAds.getCount() == 3;
        Assertions.assertTrue(wrapperAds.getResults().contains(ad));
    }

    @Test
    void getAllMyAdsTest() {
        AdEntity adEntity1 = new AdEntity();
        Ads ad = new Ads(id, imagePath, pk, price, title);
        when(adRepository.findAllByAuthorEmail(email)).thenReturn(List.of(adEntity1, adEntity));
        when(mapper.entityToAdsDto(adEntity)).thenReturn(ad);
        when(mapper.entityToAdsDto(adEntity1)).thenReturn(new Ads());

        ResponseWrapperAds wrapperAds = adService.getAllMyAds(email);
        assert wrapperAds.getCount() == 2;
        Assertions.assertTrue(wrapperAds.getResults().contains(ad));
    }
}