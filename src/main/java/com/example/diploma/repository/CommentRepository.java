package com.example.diploma.repository;

import com.example.diploma.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author anna
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
