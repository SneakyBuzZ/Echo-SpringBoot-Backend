package com.echo.echoapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echo.echoapplication.entity.UserEntity;
import com.echo.echoapplication.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll(){
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user){
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/id/{userId}")
    public ResponseEntity<UserEntity> update(@PathVariable String userId, @RequestBody UserEntity user){
        if(!userId.equals("")){
            UserEntity oldUser = userService.getById(userId).orElse(null);
            if(oldUser!=null){
                oldUser.setName(user.getName()!=null && !user.getName().equals("")? user.getName() : oldUser.getName());
                oldUser.setPassword(user.getPassword()!=null &&!user.getPassword().equals("")? user.getPassword() : oldUser.getPassword());
                userService.save(oldUser);
            }
            return new ResponseEntity<>(oldUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/id/{userName}/{userId}")
    public ResponseEntity<?> deleteById(@PathVariable String userId,@PathVariable String userName){
        userService.deleteById(userId,userName);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
