package com.example.casestudymodule4.controller;


import com.example.casestudymodule4.model.JwtResponse;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.jwt.JwtService;
import com.example.casestudymodule4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;
//    @Autowired
//    JavaMailSender javaMailSender;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> showListUser() {
        List<User> users = (List<User>) userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUserName(user.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerAccount(@RequestBody @Valid User user) {
        userService.save(user);
        return new ResponseEntity<>(userService.findById(user.getId()).get(),HttpStatus.OK);
    }
//    @PostMapping("/sendEmail/{email}") //gửi email
//    public ResponseEntity<SimpleMailMessage> sendSimpleEmail(@PathVariable String email) {
//
//        // Create a Simple MailMessage.
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(email);
//        message.setSubject("Test Simple Email");
//        message.setText("Hello, Im testing Simple Email");
//
//        // Send Message!
//        this.javaMailSender.send(message);
//
//        return new ResponseEntity<>(message,HttpStatus.OK);
//    }
    //    @PostMapping("/sendEmailUpdate/{email}") //gửi email
//    public ResponseEntity<SimpleMailMessage> sendSimpleEmail(@PathVariable String email) {
//
//        // Create a Simple MailMessage.
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setTo(email);
//        message.setSubject("Mã xác thực");
//        message.setText("Hello, Mã xác thực của bạn là 1208");
//
//        // Send Message!
//        this.javaMailSender.send(message);
//
//        return new ResponseEntity<>(message,HttpStatus.OK);
//    }

}
