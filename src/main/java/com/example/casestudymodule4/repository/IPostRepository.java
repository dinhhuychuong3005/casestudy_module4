package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepository extends JpaRepository<Post,Long> {
}
