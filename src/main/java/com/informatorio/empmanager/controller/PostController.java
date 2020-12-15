package com.informatorio.empmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatorio.empmanager.entity.Post;
import com.informatorio.empmanager.repository.PostRepository;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostRepository post;

    @GetMapping("/post")
    public List<Post> getAllPost(){
        return post.findAll();
    }
    

}