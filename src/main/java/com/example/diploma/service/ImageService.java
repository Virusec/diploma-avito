package com.example.diploma.service;

import com.example.diploma.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anna
 */

public interface ImageService {
    ImageEntity saveImage(MultipartFile image) throws IOException;

    byte[] getImage(long id) throws IOException;

    void deleteImage(ImageEntity entity) throws IOException;

    ImageEntity getEntity(long id);
}
