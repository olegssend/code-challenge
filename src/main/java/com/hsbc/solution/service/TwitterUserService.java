package com.hsbc.solution.service;

import com.hsbc.solution.entity.TwitterUser;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.entity.WallPost;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterUserService implements UserService {

    private Set<User> twitterUsers;

    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public TwitterUserService() {
        twitterUsers = new HashSet<>();
    }

    @Override
    public void addPost(String requestedUserName, String message) {
        User requestedUser = getOrCreateNewUserByUserName(requestedUserName);
        WallPost post = postService.createPost(limit140CharactersMessage(message));
        requestedUser.getWallPosts().add(post);
    }

    @Override
    public void addUserToFollowList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);
        User userToFollow = getUserByUserName(userNameToFollow);
        requestedUser.getFollowingList().add(userToFollow);
    }

    @Override
    public List<WallPost> getUsersWall(String requestedUserName) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);
        return requestedUser.getWallPosts()
                .stream()
                .sorted((p1, p2) -> p2.getPostTime().compareTo(p1.getPostTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WallPost> getUsersTimeline(String requestedUserName) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);

        List<WallPost> timeline = new ArrayList<>();
        requestedUser.getFollowingList()
                .forEach(user -> user.getWallPosts()
                        .forEach(timeline::add));

        return timeline.stream()
                .sorted((p1, p2) -> p2.getPostTime().compareTo(p1.getPostTime()))
                .collect(Collectors.toList());
    }

    private User getOrCreateNewUserByUserName(String userName) {

        for (User existedUser : twitterUsers) {
            if (existedUser.getUserName().equals(userName)) {
                return existedUser;
            }
        }

        User newUser = new TwitterUser(userName);
        twitterUsers.add(newUser);
        return newUser;
    }

    private User getUserByUserName(String userName) throws TwitterUserNotFoundException {
        for (User existedUser : twitterUsers) {
            if (existedUser.getUserName().equals(userName)) {
                return existedUser;
            }
        }
        throw new TwitterUserNotFoundException(userName + " not found.");
    }

    private String limit140CharactersMessage(String message) {
        if (message.length() <= 140) {
            return message;
        } else {
            return message.substring(0, 140);
        }
    }
}
