package com.example.casestudymodule4.service.Avatar;

import com.example.casestudymodule4.model.entity.Avatar;
import com.example.casestudymodule4.repository.AvatarRepository;
import com.example.casestudymodule4.service.IGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AvatarServiceImpl implements AvatarService {
    @Autowired
    private AvatarRepository avatarRepository;
    @Override
    public Iterable<Avatar> findAll() {
        return avatarRepository.findAll();
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarRepository.findById(id);
    }

    @Override
    public Avatar save(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    @Override
    public void remove(Long id) {
        avatarRepository.deleteById(id);
    }
}
