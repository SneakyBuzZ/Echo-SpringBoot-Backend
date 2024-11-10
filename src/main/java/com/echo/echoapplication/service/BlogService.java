package com.echo.echoapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echo.echoapplication.entity.BlogEntity;
import com.echo.echoapplication.repository.BlogRepository;


@Component
public class BlogService {
    
    @Autowired
    private BlogRepository blogRepo;

    public void save(BlogEntity blog){
        blogRepo.save(blog);
    }

    public List<BlogEntity> getAll(){
        return blogRepo.findAll();
    }

    public Optional<BlogEntity> getById(String id){
        return blogRepo.findById(id);
    }

    public void deleteById(String id){
        blogRepo.deleteById(id);
    }

}
