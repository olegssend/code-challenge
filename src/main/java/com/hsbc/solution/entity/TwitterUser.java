package com.hsbc.solution.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
public class TwitterUser implements User {
    private String userName;
    private List<User> followingList;
    private List<WallPost> wallPosts;

    public TwitterUser(String userName) {
        this.userName = userName;
        this.followingList = new ArrayList<>();
        this.wallPosts = new ArrayList<>();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public List<User> getFollowingList() {
        return followingList;
    }

    @Override
    public List<WallPost> getWallPosts() {
        return wallPosts;
    }

}
