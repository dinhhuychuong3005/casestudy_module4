package com.example.casestudymodule4.model.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;
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

    public Post(int id, String content, Date datePost, int quantityLike, int quantityComment, int view, User user, Long likes, List<Image> imgs, List<Comment> listComment) {
        this.id = id;
        this.content = content;
        this.datePost = datePost;
        this.quantityLike = quantityLike;
        this.quantityComment = quantityComment;
        this.view = view;
        this.user = user;
        this.likes = likes;
        this.imgs = imgs;
        this.listComment = listComment;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getQuantityLike() {
        return quantityLike;
    }

    public void setQuantityLike(int quantityLike) {
        this.quantityLike = quantityLike;
    }

    public int getQuantityComment() {
        return quantityComment;
    }

    public void setQuantityComment(int quantityComment) {
        this.quantityComment = quantityComment;
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

    public List<Comment> getListComment() {
        return listComment;
    }

    public void setListComment(List<Comment> listComment) {
        this.listComment = listComment;
    }
}
