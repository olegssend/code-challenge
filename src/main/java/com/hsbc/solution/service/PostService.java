package com.hsbc.solution.service;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
public interface PostService {
    Post createPostByUser(String message);

    List<? extends Post> getPostsByUser(User user);
}
