package com.example.demoTest.repository;

import com.example.demoTest.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    List<UserEntity> findAll();
}
