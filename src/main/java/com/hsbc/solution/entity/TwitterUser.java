package com.hsbc.solution.entity;

/**
 * Created by seredao on 11.06.17.
 */
public class TwitterUser implements User {
    private String userName;

    public TwitterUser(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserName() {
        return userName;
    }

}
