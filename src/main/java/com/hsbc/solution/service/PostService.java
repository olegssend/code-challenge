package com.hsbc.solution.service;

import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterException;

/**
 * Created by seredao on 11.06.17.
 */
public interface PostService {
    void createPostByUser(User user, TwitterPost twitterPost) throws TwitterException;

    void cleanup();
}
