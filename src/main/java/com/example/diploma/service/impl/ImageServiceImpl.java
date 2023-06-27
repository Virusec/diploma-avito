package com.example.diploma.service.impl;

import com.example.diploma.entity.ImageEntity;
import com.example.diploma.repository.ImageRepository;
import com.example.diploma.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * @author anna
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;
    @Value("${path.to.images}")
    private String imageDirectory;

    @Override
    public ImageEntity saveImage(MultipartFile image) throws IOException {
        ImageEntity entity = repository.save(new ImageEntity(image.getSize(), image.getContentType()));
        Path filePath = Path.of(imageDirectory, entity.getId() + "." +
                getExtensions(Objects.requireNonNull(image.getOriginalFilename()), '.'));
        Files.createDirectories(filePath.getParent());
        try (
                InputStream is = image.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
        return entity;
    }

    @Override
    public byte[] getImage(long id) {
        return new byte[0];
    }

    @Override
    public void deleteImage(ImageEntity entity) throws IOException {
        String type = entity.getMediaType();
        Files.deleteIfExists(Path.of(imageDirectory, entity.getId() + "." + getExtensions(type, '/')));
        repository.delete(entity);
    }

    private String getExtensions(String s, char delimiter) {
        return s.substring(s.lastIndexOf(delimiter) + 1);
    }
}
