package com.example.diploma.controller;

import com.example.diploma.dto.Role;
import com.example.diploma.entity.AdEntity;
import com.example.diploma.entity.ImageEntity;
import com.example.diploma.entity.UserEntity;
import com.example.diploma.mapping.AdMapper;
import com.example.diploma.mapping.UserMapper;
import com.example.diploma.repository.AdRepository;
import com.example.diploma.repository.ImageRepository;
import com.example.diploma.repository.UserRepository;
import com.example.diploma.service.impl.AdServiceImpl;
import com.example.diploma.service.impl.ImageServiceImpl;
import com.example.diploma.service.impl.UserServiceImpl;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdController.class)
class AdControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private AdServiceImpl adService;
    @SpyBean
    private ImageServiceImpl imageService;
    @SpyBean
    private UserServiceImpl userService;
    @SpyBean
    private AdMapper adMapper;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private AdRepository adRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ImageRepository imageRepository;
    private int author = 1;
    private final int pk = 1;
    private final int price = 100;
    private final String title = "little mouse";
    private final String description = "description";
    private final String email = "aaa@ccc.com";
    private final String fName = "Oleg";
    private final String lName = "Olegov";
    private final String phone = "+78008889922";
    private final String pass = "password";
    private UserEntity user;
    private ImageEntity adImage;

    @BeforeEach
    void setUp() {
        user = new UserEntity(author, pass, email, fName, lName, phone, Role.USER, null);
        adImage = new ImageEntity(11L);
    }

    @Test
    @WithMockUser(username = email)
    void getAllAdsTest() throws Exception {
        int pk2 = 2;
        int price2 = 200;
        String description2 = "description2";
        String title2 = "big cat";
        AdEntity entity1 = new AdEntity(pk, user, title, price, description, adImage);
        AdEntity entity2 = new AdEntity(pk2, user, title2, price2, description2, adImage);
        when(adRepository.findAll()).thenReturn(List.of(entity1, entity2));
        mockMvc.perform(MockMvcRequestBuilders.get("/ads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(2));
    }

    //TODO доделать этот тест
     @Test
     @WithMockUser(username = email)
     void addAd() throws Exception {
         byte[] inputArray = "Test".getBytes();
         MockMultipartFile mockMultipartFile = new MockMultipartFile("image", "image", MediaType.IMAGE_JPEG_VALUE, inputArray);
         Authentication a = SecurityContextHolder.getContext().getAuthentication();

         JSONObject createAdsJson = new JSONObject();
         createAdsJson.put("description", description);
         createAdsJson.put("price", price);
         createAdsJson.put("title", title);
         Map<String, Object> param =  new HashMap<>(2);
         param.put("properties",createAdsJson);
         param.put("auth", a);
         mockMvc.perform(multipart("/ads")
                         .file(mockMultipartFile)
                         .flashAttrs(param));

    }
    @Test
    @WithMockUser(username = email)
    void getAdsTest() throws Exception {
        AdEntity entity = new AdEntity(pk, user, title, price, description, adImage);
        when(adRepository.findById(pk)).thenReturn(Optional.of(entity));
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}", pk)
                        .content(String.valueOf(pk))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.description").value(description))
                .andExpect(jsonPath("$.price").value(price))
                .andExpect(jsonPath("$.pk").value(pk))
                .andExpect(jsonPath("$.authorFirstName").value(fName))
                .andExpect(jsonPath("$.authorLastName").value(lName))
                .andExpect(jsonPath("$.phone").value(phone))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    void getAdsUnauthorizedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ads/{id}", pk)
                        .content(String.valueOf(pk))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void removeAd() {
    }

    @Test
    void updateAds() {
    }

    @Test
    void getAdsMe() {
    }

    @Test
    void updateImage() {
    }

    @Test
    void getImage() {
    }
}