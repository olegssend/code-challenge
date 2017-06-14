package com.hsbc.solution.service;

import com.hsbc.solution.dao.PostDao;
import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.entity.User;
import com.hsbc.solution.exception.TwitterException;
import com.hsbc.solution.validator.TwitterPostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterPostService implements PostService {

    private PostDao postDao;
    private TwitterPostValidator postValidator;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Autowired
    public void setPostValidator(TwitterPostValidator postValidator) {
        this.postValidator = postValidator;
    }

    @Override
    public void createPostByUser(User user, TwitterPost twitterPost) throws TwitterException {
        postValidator.validate(twitterPost);
        postDao.saveUsersPost(user, twitterPost);
    }

    @Override
    public void cleanup() {
        postDao.cleanup();
    }

}
