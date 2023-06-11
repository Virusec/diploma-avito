package com.example.diploma.repository;

import com.example.diploma.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author anna
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByAd(int id);
}
