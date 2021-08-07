package com.example.casestudymodule4.controller;

import com.example.casestudymodule4.model.entity.Friend;
import com.example.casestudymodule4.model.entity.User;
import com.example.casestudymodule4.service.friend.IFriendService;
import com.example.casestudymodule4.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/friend")
public class FriendController {
    @Autowired
    private IFriendService friendService;
    @Autowired
    private IUserService userService;

    @GetMapping("/listFriend")
    public ResponseEntity<Iterable<Friend>> getAll() {
        Iterable<Friend> listFriend = friendService.findAll();
        return new ResponseEntity<>(listFriend, HttpStatus.OK);
    }

    @GetMapping("/allMutualFriend/{idUser1}/{idUser2}")
    public ResponseEntity<List<User>> getMutualFriend(@PathVariable Long idUser1, @PathVariable Long idUser2) {
        List<User> listMutualFriend = new ArrayList<>();
        List<User> listUserFriend1 = getListUserFriend(idUser1);
        List<User> listUserFriend2 = getListUserFriend(idUser2);

        for (User user1 : listUserFriend1) {

            for (User user2 : listUserFriend2) {

                if (user1.getId() == user2.getId()) {
                    listMutualFriend.add(user1);
                }
            }
        }

        return new ResponseEntity<>(listMutualFriend, HttpStatus.OK);
    }

    @GetMapping("/listUser/{idUser}")
    public ResponseEntity<Iterable<User>> listUser(@PathVariable Long idUser) {
        List<User> userUnFriend = new ArrayList<>();
        List<User> users = (List<User>) userService.findAll();
        List<User> users1 = getListUserFriend1(idUser);
        for (User user : users) {
            if (!users1.contains(user) && (user.getId() != idUser)) {
                userUnFriend.add(user);
            }
        }
        return new ResponseEntity<>(userUnFriend, HttpStatus.OK);
    }


    @GetMapping("/listFriend/{idUser}")
    public ResponseEntity<List<com.example.casestudymodule4.model.entity.User>> getAllFriendById(@PathVariable Long idUser) {
        List<User> listUserFriend = getListUserFriend(idUser);
        return new ResponseEntity<>(listUserFriend, HttpStatus.OK);
    }

    public List<User> getListUserFriend1(Long idUser) {
        List<Friend> listFriend = friendService.findAllFriendById(idUser);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity : listFriend) {
            com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getIdFriendOfUser()).get();
            listUser.add(user);
        }
        return listUser;
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

    @GetMapping("/addFriend/{idUs}/{idFr}")
    public ResponseEntity<Friend> addFriend(@PathVariable Long idUs, @PathVariable Long idFr) {
        User user = userService.findById(idUs).get();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        Friend friend = new Friend(date, 2, user, idFr);
        friendService.save(friend);
        return new ResponseEntity<>(friend, HttpStatus.OK);
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<Friend> acceptFriend(@PathVariable Long id) {
        Friend friend = friendService.findById(id).get();
        friend.setStatus(1);
        friendService.save(friend);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFriend/{idU1}/{idU2}")
    public ResponseEntity<Friend> deleteFriend(@PathVariable Long idU1, @PathVariable Long idU2) {
        Friend friend = friendService.findFriendByIdUser(idU1, idU2);
        System.out.println(friend.getId());
        friendService.remove(friend.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
@GetMapping("/listAdd/{idFr}")
    public ResponseEntity<List<Friend>> getListFriendAdd(@PathVariable Long idFr) {
        List<Friend> list = friendService.findAllFriendAddById(idFr);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
