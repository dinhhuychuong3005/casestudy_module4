package com.example.casestudymodule4.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Date datePost;
    private int quantityLike;
    private int quantityComment;
    private int view;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Long likes;
    @OneToMany(mappedBy = "postId", fetch = FetchType.EAGER)
    private List<Image> imgs ;

    @OneToMany(targetEntity = Comment.class)
    private List<Comment> listComment;
}
