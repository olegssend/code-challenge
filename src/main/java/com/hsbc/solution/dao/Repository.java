package com.hsbc.solution.dao;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by osereda on 2017-06-14.
 */
@Component
public class Repository {
    private Set<User> users = new HashSet<>();
    private Map<User, List<Post>> usersPosts = new HashMap<>();
    private Map<User, List<User>> usersFollowings = new HashMap<>();

    public Set<User> getUsers() {
        return users;
    }

    public Map<User, List<Post>> getUsersPosts() {
        return usersPosts;
    }

    public Map<User, List<User>> getUsersFollowings() {
        return usersFollowings;
    }
}
