package com.hsbc.solution.service;

import com.hsbc.solution.entity.WallPost;
import com.hsbc.solution.exception.TwitterUserNotFoundException;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
public interface UserService {
    void addPost(String requestedUserName, String message);

    void addUserToFollowList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException;

    List<WallPost> getUsersWall(String requestedUserName) throws TwitterUserNotFoundException;

    List<WallPost> getUsersTimeline(String requestedUserName) throws TwitterUserNotFoundException;
}
