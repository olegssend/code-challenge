package com.hsbc.solution.service;

import com.hsbc.solution.dao.PostDao;
import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterPostService implements PostService {

    private PostDao postDao;

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post createPost(String message) {
//        return new TwitterPost(id, message, new Date());
        return null;
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return null;
    }

}
