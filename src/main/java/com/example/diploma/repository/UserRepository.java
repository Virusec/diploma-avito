package com.example.diploma.repository;

import org.springframework.stereotype.Repository;
/**
 * @author anna
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
