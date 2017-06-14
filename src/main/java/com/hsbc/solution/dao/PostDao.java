package com.hsbc.solution.dao;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;

/**
 * Created by osereda on 2017-06-13.
 */
public interface PostDao {
    void saveUsersPost(User user, Post post);

    void cleanup();
}
