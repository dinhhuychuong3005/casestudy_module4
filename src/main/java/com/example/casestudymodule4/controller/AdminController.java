package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll(){
       List<User> users = (List<User>) userService.findAll();
        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> disableUser(@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userOptional.get().setEnabled(false) ;
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

}
