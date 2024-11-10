package com.echo.echoapplication.controller;

import java.util.List;
import java.util.Optional;

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
import com.echo.echoapplication.entity.BlogEntity;
import com.echo.echoapplication.entity.UserEntity;
import com.echo.echoapplication.service.BlogService;
import com.echo.echoapplication.service.UserService;

@RestController
@RequestMapping("/blogs")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<BlogEntity> getAll(){
        return blogService.getAll();
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<List<BlogEntity>> getUserBlogs(@PathVariable String userName){
        UserEntity user = userService.findByName(userName);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<BlogEntity> userBlogs = user.getUserBlogs();
        return new ResponseEntity<>(userBlogs,HttpStatus.OK);
    }

    @PostMapping("/user/{userName}")
    public ResponseEntity<BlogEntity> create(@RequestBody BlogEntity blog, @PathVariable String userName){
        try {
            blogService.save(blog);
            UserEntity user = userService.findByName(userName);
            user.getUserBlogs().add(blog);
            userService.save(user);
            return new ResponseEntity<>(blog,HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{blogId}")
    public ResponseEntity<BlogEntity> getById(@PathVariable String blogId){
        Optional<BlogEntity> blog = blogService.getById(blogId);
        if(blog.isPresent()){
            return new ResponseEntity<>(blog.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{blogId}")
    public ResponseEntity<BlogEntity> update(@PathVariable String blogId,@RequestBody BlogEntity blog,@PathVariable String userName){
        try {
            BlogEntity oldBlog = blogService.getById(blogId).orElse(null);
            if(oldBlog != null){
                oldBlog.setTitle(blog.getTitle() != null && !blog.getTitle().equals("") ? blog.getTitle() : oldBlog.getTitle());
                oldBlog.setDescription(blog.getDescription() != null &&!blog.getDescription().equals("")? blog.getDescription() : oldBlog.getDescription());
            }
            blogService.save(oldBlog);
            return new ResponseEntity<>(oldBlog, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id/{blogId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String blogId){
       blogService.deleteById(blogId);
        return new ResponseEntity<>(true,HttpStatus.NO_CONTENT);
    }
}
