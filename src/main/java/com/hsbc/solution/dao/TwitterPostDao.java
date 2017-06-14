package com.hsbc.solution.dao;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by osereda on 2017-06-13.
 */
@Component
public class TwitterPostDao implements PostDao {

    private static int nextPostId;

    private Repository repository;

    @Autowired
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void saveUsersPost(User user, Post post) {
        post.setId(nextPostId);
        nextPostId++;
        repository.getUsersPosts().get(user).add(post);
    }

    @Override
    public void cleanup() {
        repository.getUsersPosts().clear();
        nextPostId = 0;
    }
}
