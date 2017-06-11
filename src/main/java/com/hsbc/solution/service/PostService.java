package com.hsbc.solution.service;

import com.hsbc.solution.entity.WallPost;

/**
 * Created by seredao on 11.06.17.
 */
public interface PostService {
    WallPost createPost(String message);
}
