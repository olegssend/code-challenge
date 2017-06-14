package com.hsbc.solution.service;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.exception.TwitterException;
import com.hsbc.solution.exception.TwitterUserNotFoundException;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
public interface UserService {
    void addPost(String requestedUserName, TwitterPost twitterPost) throws TwitterException;

    void addUserToFollowList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException;

    List<Post> getUsersWall(String requestedUserName) throws TwitterUserNotFoundException;

    List<Post> getUsersTimeline(String requestedUserName) throws TwitterUserNotFoundException;

    void cleanup();
}
