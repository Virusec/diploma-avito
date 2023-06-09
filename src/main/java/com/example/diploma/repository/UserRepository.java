package com.example.diploma.repository;

import com.example.diploma.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author anna
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
