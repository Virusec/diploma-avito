package com.example.diploma.repository;

import com.example.diploma.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anna
 */

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
}
