package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.Friend;
import com.example.casestudymodule4.model.entity.Post;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.friend.IFriendService;
import com.example.casestudymodule4.service.post.IPostService;
import com.example.casestudymodule4.service.user.IUserService;
import com.example.casestudymodule4.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;
    @Autowired
    private IFriendService friendService;

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    public List<User> getListUserFriend(Long idUser) {
        List<Friend> listFriend = friendService.findAllFriendById(idUser);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity : listFriend) {

            if (friendEntity.getStatus() == 1) {
                com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getIdFriendOfUser()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

    public List<User> getListUserFriendByFr(Long idFr) {
        List<Friend> listFriend = friendService.findAllFriendByIdFr(idFr);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity : listFriend) {

            if (friendEntity.getStatus() == 1) {
                com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getUser().getId()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

    public List<User> showListFriend(Long id) {
        List<User> users = getListUserFriend(id);
        List<User> users1 = getListUserFriendByFr(id);
        for (int i = 0; i < users.size(); i++) {
            users1.add(users.get(i));
        }
        return users1;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Post>> findAllByIdUs(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<Iterable<Post>> findAllByIdUsAndByView(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findAllByUserIdAndByStatus(id), HttpStatus.OK);
    }

    @GetMapping("/listPostFriend/{id}")
    public ResponseEntity<Iterable<Post>> getAllPostFriend(@PathVariable Long id) {
        List<User> users = showListFriend(id);
        List<Post> posts ;
        List<Post> posts1 = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getId());
                posts = (List<Post>) postService.findAllByUserIdAndByStatus(users.get(i).getId());
            if (postService.findAllByUserIdAndByStatus(users.get(i).getId()) != null) {
                for (int j = 0; j < posts.size(); j++) {
                    posts1.add(posts.get(j));
                }
            }

        }
        return new ResponseEntity<>(posts1, HttpStatus.OK);
    }
    @GetMapping("/views/{id}")
    public ResponseEntity<Post> checkView(@PathVariable Long id){
        Optional<Post> post = postService.findById(id);
        post.get().setView(post.get().getView()+1);
        postService.save(post.get());
        return new ResponseEntity<>(post.get(),HttpStatus.OK);
    }
}

