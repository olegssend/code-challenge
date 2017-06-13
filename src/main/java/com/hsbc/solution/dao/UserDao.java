package com.hsbc.solution.dao;

import com.hsbc.solution.entity.User;

import java.util.List;

/**
 * Created by osereda on 2017-06-13.
 */
public interface UserDao {
    User getUserByUserName(String userName);

    User getOrCreateNewUserByUserName(String userName);

    List<User> getFollowingListForUserName(String requestedUserName);
}
