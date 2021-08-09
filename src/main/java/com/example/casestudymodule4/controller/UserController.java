package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PutMapping("/editPassword/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id,@RequestBody User user){
        Optional<User> user1 = userService.findById(id);
        user.setId(user1.get().getId());
        user.setUsername(user1.get().getUsername());
        user.setAddress(user1.get().getAddress());
        user.setAvatar(user1.get().getAvatar());
        user.setEmail(user1.get().getEmail());
        user.setDateOfBirth(user1.get().getDateOfBirth());
        user.setFullName(user1.get().getFullName());
        user.setGender(user1.get().getGender());
        user.setNumberPhone(user1.get().getNumberPhone());
        user.setRoles(user1.get().getRoles());
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
