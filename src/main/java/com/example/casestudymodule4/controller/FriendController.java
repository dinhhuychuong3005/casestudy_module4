package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.Friend;
import com.example.casestudymodule4.service.friend.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Repository
@RestController("/api/friend")
public class FriendController {
    @Autowired
    private IFriendService friendService;
    @PostMapping
   public ResponseEntity<Friend> createFriend(@RequestBody Friend friend){
        return  new ResponseEntity<>(friendService.save(friend), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<Iterable<Friend>> getListFriend(){
        return new ResponseEntity<>(friendService.findAll(),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Friend> deleteFriend(@PathVariable Long id){
        Optional<Friend> friendOptional = friendService.findById(id);
        if(!friendOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friendService.remove(id);
        return new ResponseEntity<>(friendOptional.get(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<Friend> findByIdFriend(@PathVariable Long id){
        Optional<Friend> friendOptional = friendService.findById(id);
        if(!friendOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(friendOptional.get(),HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Friend> updateEditFriend(@PathVariable Long id,@RequestBody Friend friend){
        Optional<Friend> friendOptional =friendService.findById(id);
        if(!friendOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        friend.setId(friendOptional.get().getId());
        return new ResponseEntity<>(friendService.save(friend),HttpStatus.OK);
    }



}
