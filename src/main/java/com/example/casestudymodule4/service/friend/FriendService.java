package com.example.casestudymodule4.service.friend;


import com.example.casestudymodule4.model.entity.Friend;

import com.example.casestudymodule4.repository.IFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

import java.util.List;


@Service
public class FriendService implements IFriendService {
    @Autowired
    private IFriendRepository friendRepository;

    @Override
    public Iterable<Friend> findAll() {
        return friendRepository.findAll();
    }

    @Override
    public Optional<Friend> findById(Long id) {
        return friendRepository.findById(id);
    }

    @Override
    public Friend save(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void remove(Long id) {
        friendRepository.deleteById(id);
    }


    @Override
    public List<Friend> findAllFriendById(Long id) {
        return friendRepository.findAllFriendById(id);
    }

    @Override
    public Friend findFriendByIdUser(Long idF, Long idU) {
        return friendRepository.findFriendByIdUser(idF, idU);
    }

    @Override
    public Friend findByUserIdAndIdFriendOfUser(Long userId, Long idUserFriend) {
        return friendRepository.findByUserIdAndIdFriendOfUser(userId, idUserFriend);
    }

}
