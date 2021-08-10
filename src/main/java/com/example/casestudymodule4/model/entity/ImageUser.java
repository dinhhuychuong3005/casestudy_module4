package com.example.casestudymodule4.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
