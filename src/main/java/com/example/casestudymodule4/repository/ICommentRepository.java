package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
