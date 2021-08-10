package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    @Query("select p from Post p where p.user.id=:id")
    public Iterable<Post> findAllByUserId(Long id);
    @Query("select p from Post p where p.user.id=:id and p.view =1")
    public Iterable<Post> findAllByUserIdAndByView(Long id);
}
