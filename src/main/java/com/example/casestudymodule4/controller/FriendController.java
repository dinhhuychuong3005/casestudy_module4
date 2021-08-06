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
    public ResponseEntity<List<User>> getMutualFriend(@PathVariable Long idUser1,@PathVariable Long idUser2) {
        List<User> listMutualFriend = new ArrayList<>();
        List<User> listUserFriend1 = getListUserFriend(idUser1);
        List<User> listUserFriend2 = getListUserFriend(idUser2);

        for (User user1: listUserFriend1){
            System.out.println(user1.getId() + "thàng 1");
            for (User user2:listUserFriend2){
                System.out.println(user2.getId() + "thàng 2");
                if (user1.getId() == user2.getId()){
                    listMutualFriend.add(user1);
                }
            }
        }

        return new ResponseEntity<>(listMutualFriend, HttpStatus.OK);
    }
    @GetMapping("/listFriend/{idUser}")
    public ResponseEntity<List<com.example.casestudymodule4.model.entity.User>> getAllFriendById(@PathVariable Long idUser) {
        List<User> listUserFriend = getListUserFriend(idUser);
        return new ResponseEntity<>(listUserFriend, HttpStatus.OK);
    }
    public List<User> getListUserFriend(Long idUser){
        List<Friend> listFriend = friendService.findAllFriendById(idUser);
        List<com.example.casestudymodule4.model.entity.User> listUser = new ArrayList<>();
        for (Friend friendEntity: listFriend){
            System.out.println("id bạn " + friendEntity.getId());
            if (friendEntity.getStatus() != 0){
                com.example.casestudymodule4.model.entity.User user = userService.findById(friendEntity.getIdFriendOfUser()).get();
                listUser.add(user);
            }
        }
        return listUser;
    }

}
