package com.example.casestudymodule4.service.post;

import com.example.casestudymodule4.model.entity.Post;
import com.example.casestudymodule4.service.IGeneralService;

public interface IPostService extends IGeneralService<Post> {
    Iterable<Post> findAllByContentContaining(String Content);
}
