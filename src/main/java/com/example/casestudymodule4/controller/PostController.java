package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.Post;
import com.example.casestudymodule4.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> SearchByContent(@RequestParam String q) {

        return new ResponseEntity<>(postService.findAllByContentContaining(q), HttpStatus.OK);
    }
}

