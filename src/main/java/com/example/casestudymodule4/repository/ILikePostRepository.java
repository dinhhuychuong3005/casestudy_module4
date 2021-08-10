package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.entity.LikePost;
import com.example.casestudymodule4.model.entity.Role;
import com.example.casestudymodule4.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILikePostRepository extends JpaRepository <LikePost, Long> {
//    LikePost findByLikePostName(String name);
}
