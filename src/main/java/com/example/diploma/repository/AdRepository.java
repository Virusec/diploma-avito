package com.example.diploma.repository;

import com.example.diploma.entity.AdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anna
 */
@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {
}
