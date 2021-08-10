package com.example.casestudymodule4.controller;


import com.example.casestudymodule4.model.entity.Comment;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.Comment.ICommentService;
import com.example.casestudymodule4.service.user.IUserService;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/Comment")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<Comment> createComment(@PathVariable Long id,@RequestBody String comment) {
        User user= userService.findById(id).get();
        long millis = System.currentTimeMillis();
        java.sql.Date dateComment = new java.sql.Date(millis);

        Comment comment1 = new Comment(comment,dateComment,user);
        return new ResponseEntity<>(commentService.save(comment1), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Comment>> showListComment() {
        return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.remove(id);
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> saveEditComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setId(commentOptional.get().getId());
        return new ResponseEntity<>(commentService.save(comment),HttpStatus.OK);
    }
}