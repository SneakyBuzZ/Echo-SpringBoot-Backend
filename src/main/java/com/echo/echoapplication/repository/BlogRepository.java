package com.echo.echoapplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.echo.echoapplication.entity.BlogEntity;

public interface BlogRepository extends MongoRepository<BlogEntity , String>{

}