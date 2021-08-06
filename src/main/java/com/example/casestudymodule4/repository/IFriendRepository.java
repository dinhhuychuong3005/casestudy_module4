package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFriendRepository extends JpaRepository<Friend,Long > {
}
