package com.example.diploma.service.impl;

import com.example.diploma.entity.ImageEntity;
import com.example.diploma.exception.FindNoEntityException;
import com.example.diploma.repository.ImageRepository;
import com.example.diploma.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * @author anna
 */

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;
    @Value("${path.to.images}")
    private String imageDirectory;

    @Override
    public ImageEntity saveImage(MultipartFile image) throws IOException {
        ImageEntity entity = repository.save(new ImageEntity());
        Path filePath = getPath(entity);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(getPath(entity));
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
    public byte[] getImage(long id) throws IOException {
        return Files.readAllBytes(getPath(getEntity(id)));
    }

    @Override
    public ImageEntity getEntity(long id) {
        return repository.findById(id).orElseThrow(() -> new FindNoEntityException("картинка"));
    }

    @Override
    public void deleteImage(ImageEntity image) throws IOException {
        Files.deleteIfExists(getPath(image));
        repository.delete(image);
    }

    private Path getPath(ImageEntity image) {
        return Path.of(imageDirectory, String.valueOf(image.getId()));
    }
}
