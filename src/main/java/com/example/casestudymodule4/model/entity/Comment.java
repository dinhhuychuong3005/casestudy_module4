package com.example.casestudymodule4.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Date dateComment;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
