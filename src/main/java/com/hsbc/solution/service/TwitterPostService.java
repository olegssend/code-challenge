package com.hsbc.solution.service;

import com.hsbc.solution.entity.WallPost;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by seredao on 11.06.17.
 */
@Service
public class TwitterPostService implements PostService {
    @Override
    public WallPost createPost(String message) {
        return new WallPost(message, new Date());
    }

}
