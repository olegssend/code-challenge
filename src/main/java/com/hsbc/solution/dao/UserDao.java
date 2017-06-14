package com.hsbc.solution.dao;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterUserNotFoundException;

import java.util.List;

/**
 * Created by osereda on 2017-06-13.
 */
public interface UserDao {
    User getUserByUserName(String userName) throws TwitterUserNotFoundException;

    User getOrCreateNewUserByUserName(String userName);

    List<User> getFollowingListForUser(User requestedUser);

    void addUserToUsersFollowingList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException;

    List<Post> getUsersWall(User requestedUser);

    void cleanup();
}
