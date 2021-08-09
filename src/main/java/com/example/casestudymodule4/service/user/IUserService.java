package com.example.casestudymodule4.service.user;


import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.IGeneralService;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends  UserDetailsService {
    void save(User user);
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    User findByUsername(String username);
    User getCurrentUser();
    UserDetails loadUserById(Long id);
    boolean checkLogin(User user);
    void delete(User user);



}
