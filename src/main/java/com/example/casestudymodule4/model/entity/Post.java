package com.example.casestudymodule4.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Date datePost;
    private int status;
    private int view;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Long likes;
    @OneToMany(mappedBy = "postId", fetch = FetchType.EAGER)
    private List<Image> imgs;



    public Post() {
    }

    public Post(String content, Date datePost, int status, int view, User user, Long likes, List<Image> imgs) {
        this.content = content;
        this.datePost = datePost;
        this.status = status;
        this.view = view;
        this.user = user;
        this.likes = likes;
        this.imgs = imgs;

    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }


    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public List<Image> getImgs() {
        return imgs;
    }

    public void setImgs(List<Image> imgs) {
        this.imgs = imgs;
    }

}
