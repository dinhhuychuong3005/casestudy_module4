package com.example.casestudymodule4.service.likepost;

import com.example.casestudymodule4.model.entity.LikePost;
import com.example.casestudymodule4.repository.ILikePostRepository;
import com.example.casestudymodule4.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePostService implements ILikePostService {

    @Autowired
    private ILikePostRepository iLikePostRepository;

    @Override
    public Iterable<LikePost> findAll() {
        return iLikePostRepository.findAll();
    }

    @Override
    public Optional<LikePost> findById(Long id) {
        return iLikePostRepository.findById(id);
    }

    @Override
    public LikePost save(LikePost likePost) {
        return iLikePostRepository.save(likePost);
    }

    @Override
    public void remove(Long id) {
        iLikePostRepository.deleteById(id);
    }

//    @Override
//    public LikePost findByLikePostName(String name) {
//        return iLikePostRepository.findByLikePostName(name);
//    }
}
