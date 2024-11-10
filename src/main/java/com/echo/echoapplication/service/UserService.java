package com.echo.echoapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echo.echoapplication.entity.UserEntity;
import com.echo.echoapplication.repository.UserRepository;


@Component
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;


    public void save(UserEntity blog){
        userRepo.save(blog);
    }

    public List<UserEntity> getAll(){
        return userRepo.findAll();
    }

    public Optional<UserEntity> getById(String id){
        return userRepo.findById(id);
    }

    public void deleteById(String id, String userName){
        UserEntity user = userService.findByName(userName);
        user.getUserBlogs().removeIf(x -> x.getId().equals(id));
        userService.save(user);
        userRepo.deleteById(id);
    }

    public UserEntity findByName(String name){
        return userRepo.findByName(name);
    }
}
