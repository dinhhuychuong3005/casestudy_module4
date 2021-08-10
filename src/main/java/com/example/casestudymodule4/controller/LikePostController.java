package com.example.casestudymodule4.controller;


import com.example.casestudymodule4.model.entity.LikePost;
import com.example.casestudymodule4.service.likepost.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/likepost")
public class LikePostController {

    @Autowired
    private ILikePostService likePostService;

    @GetMapping
    public ResponseEntity<Iterable<LikePost>> showListLikePost() {
        return new ResponseEntity<>(likePostService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LikePost> createLikePost(@RequestBody LikePost likePost) {
        return new ResponseEntity<>(likePostService.save(likePost), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LikePost> deleteLikePost(@PathVariable Long id) {
        Optional<LikePost> likePostOptional = likePostService.findById(id);
        if (!likePostOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        likePostService.remove(id);
        return new ResponseEntity<>(likePostOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LikePost> saveEditLikePost(@PathVariable Long id, @RequestBody LikePost likePost) {
        Optional<LikePost> likePostOptional = likePostService.findById(id);
        if (!likePostOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        likePost.setId(likePostOptional.get().getId());
        return new ResponseEntity<>(likePostService.save(likePost),HttpStatus.OK);
    }
}
