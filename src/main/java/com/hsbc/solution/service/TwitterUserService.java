package com.hsbc.solution.service;

import com.hsbc.solution.dao.UserDao;
import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterException;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterUserService implements UserService {

    private UserDao userDao;
    private PostService postService;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addPost(String requestedUserName, TwitterPost twitterPost) throws TwitterException {
        User requestedUser = getOrCreateNewUserByUserName(requestedUserName);
        postService.createPostByUser(requestedUser, twitterPost);
    }

    @Override
    public void addUserToFollowList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException {
        userDao.addUserToUsersFollowingList(requestedUserName, userNameToFollow);
    }

    @Override
    public List<Post> getUsersWall(String requestedUserName) throws TwitterUserNotFoundException {
        User requestedUser = userDao.getUserByUserName(requestedUserName);
        return userDao.getUsersWall(requestedUser).stream()
                .sorted(Comparator.comparing(Post::getPostTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getUsersTimeline(String requestedUserName) throws TwitterUserNotFoundException {

        List<Post> timeline = new ArrayList<>();
        User requestedUser = userDao.getUserByUserName(requestedUserName);
        userDao.getFollowingListForUser(requestedUser)
                .forEach(user -> timeline.addAll(userDao.getUsersWall(user)));

        return timeline.stream()
                .sorted(Comparator.comparing(Post::getPostTime))
                .collect(Collectors.toList());
    }

    @Override
    public void cleanup() {
        userDao.cleanup();
        postService.cleanup();
    }

    private User getOrCreateNewUserByUserName(String userName) {
        return userDao.getOrCreateNewUserByUserName(userName);
    }

}
