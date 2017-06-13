package com.hsbc.solution.service;

import com.hsbc.solution.dao.UserDao;
import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterException;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import com.hsbc.solution.validator.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterUserService implements UserService {

    private UserDao userDao;
    private PostService postService;
    private MessageValidator messageValidator;

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    public void setMessageValidator(MessageValidator messageValidator) {
        this.messageValidator = messageValidator;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addPost(String requestedUserName, String message) throws TwitterException {
        messageValidator.validate(message);
        User requestedUser = getOrCreateNewUserByUserName(requestedUserName);
        Post post = postService.createPost(message);
        requestedUser.getWallPosts().add(post);
    }

    @Override
    public void addUserToFollowList(String requestedUserName, String userNameToFollow) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);
        User userToFollow = getUserByUserName(userNameToFollow);
        requestedUser.getFollowingList().add(userToFollow);
    }

    @Override
    public List<Post> getUsersWall(String requestedUserName) throws TwitterUserNotFoundException {
        User requestedUser = getUserByUserName(requestedUserName);
        return requestedUser.getWallPosts()
                .stream()
                .sorted((p1, p2) -> p2.getPostTime().compareTo(p1.getPostTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getUsersTimeline(String requestedUserName) throws TwitterUserNotFoundException {

        List<Post> timeline = new ArrayList<>();
        userDao.getFollowingListForUserName(requestedUserName)
                .forEach(user -> timeline.addAll(postService.getPostsByUser(user)));

        return timeline.stream()
                .sorted((p1, p2) -> p2.getPostTime().compareTo(p1.getPostTime()))
                .collect(Collectors.toList());
    }

    private User getOrCreateNewUserByUserName(String userName) {

        return userDao.getOrCreateNewUserByUserName(userName);
//        Optional<User> userOptional = twitterUsers.stream()
//                .filter(user -> user.getUserName().equals(userName))
//                .findFirst();
//
//        return userOptional.orElseGet(() -> {
//            User u = new TwitterUser(userName);
//            twitterUsers.add(u);
//            return u;
//        });
    }

    private User getUserByUserName(String userName) throws TwitterUserNotFoundException {

        Optional<User> userOptional = Optional.of(userDao.getUserByUserName(userName));
        return userOptional.orElseThrow(() -> new TwitterUserNotFoundException(userName + " not found"));

//        return twitterUsers.stream()
//                .filter(user -> user.getUserName().equals(userName))
//                .findFirst()
//                .orElseThrow(() -> new TwitterUserNotFoundException(userName + " not found."));
    }
}
