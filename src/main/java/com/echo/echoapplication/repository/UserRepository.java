package com.echo.echoapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.echo.echoapplication.entity.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity , String>{
    UserEntity findByName(String name);
}
