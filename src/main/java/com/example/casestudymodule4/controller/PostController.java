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
        return new ResponseEntity<>(postService.findAllByUserIdAndByView(id), HttpStatus.OK);
    }

    @GetMapping("/listPostFriend/{id}")
    public ResponseEntity<Iterable<Post>> getAllPostFriend(@PathVariable Long id) {
        List<User> users = showListFriend(id);
        List<Post> posts ;
        List<Post> posts1 = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getId());
                posts = (List<Post>) postService.findAllByUserIdAndByView(users.get(i).getId());
            if (postService.findAllByUserIdAndByView(users.get(i).getId()) != null) {
                for (int j = 0; j < posts.size(); j++) {
                    posts1.add(posts.get(j));
                }
            }

        }
        return new ResponseEntity<>(posts1, HttpStatus.OK);
    }
}

