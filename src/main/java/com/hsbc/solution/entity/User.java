package com.hsbc.solution.entity;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
public interface User {
    String getUserName();

    List<WallPost> getWallPosts();

    List<User> getFollowingList();
}
