package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {
}
