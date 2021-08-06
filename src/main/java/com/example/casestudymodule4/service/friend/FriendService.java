package com.example.casestudymodule4.service.friend;

import com.example.casestudymodule4.model.Friend;
import com.example.casestudymodule4.repository.IFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
}
