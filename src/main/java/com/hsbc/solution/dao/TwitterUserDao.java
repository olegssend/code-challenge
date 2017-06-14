package com.hsbc.solution.dao;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.TwitterUser;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by osereda on 2017-06-13.
 */
@Component
public class TwitterUserDao implements UserDao {

    private Repository repository;

    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public User getUserByUserName(String userName) throws TwitterUserNotFoundException {
        return repository.getUsers().stream()
                .filter(user -> userName.equalsIgnoreCase(user.getUserName()))
                .findFirst()
                .orElseThrow(() -> new TwitterUserNotFoundException(userName + " not found."));
    }

    @Override
    public User getOrCreateNewUserByUserName(String userName) {
        return repository.getUsers().stream()
                .filter(u -> userName.equalsIgnoreCase(u.getUserName()))
                .findFirst()
                .orElseGet(() -> createNewUser(userName));
    }

    private User createNewUser(String userName) {
        User user = new TwitterUser(userName);
        repository.getUsers().add(user);
        repository.getUsersPosts().put(user, new ArrayList<>());
        repository.getUsersFollowings().put(user, new ArrayList<>());
        return user;
    }

    @Override
    public List<User> getFollowingListForUser(User requestedUser) {
        return repository.getUsersFollowings().get(requestedUser);
    }

    @Override
    public void addUserToUsersFollowingList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);
        User userToFollow = getUserByUserName(userNameToFollow);
        repository.getUsersFollowings().get(requestedUser).add(userToFollow);
    }

    @Override
    public List<Post> getUsersWall(User requestedUser) {
        return repository.getUsersPosts().get(requestedUser);
    }

    @Override
    public void cleanup() {
        repository.getUsers().clear();
        repository.getUsersFollowings().clear();
    }

}
