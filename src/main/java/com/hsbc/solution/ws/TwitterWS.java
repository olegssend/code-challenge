package com.hsbc.solution.ws;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.exception.MessageTooLongException;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import com.hsbc.solution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
@RestController
@RequestMapping(path = "/")
public class TwitterWS {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postMessage(String message,
                                      @HeaderParam("userId") String requestedUserName) {

        try {
            userService.addPost(requestedUserName, message);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (MessageTooLongException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "follow")
    public ResponseEntity followUser(@RequestParam("userNameToFollow") String userNameToFollow,
                               @HeaderParam("userId") String requestedUserName) {

        try {
            userService.addUserToFollowList(requestedUserName, userNameToFollow);
            return new ResponseEntity(HttpStatus.OK);
        } catch (TwitterUserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "wall")
    public ResponseEntity<?> getWall(@HeaderParam("userId") String requestedUserName) {

        try {
            List<Post> wall = userService.getUsersWall(requestedUserName);

            return new ResponseEntity<>(wall, HttpStatus.OK);
        } catch (TwitterUserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "timeline")
    public ResponseEntity<?> getTimeline(@HeaderParam("userId") String requestedUserName) {

        try {
            List<Post> timeline = userService.getUsersTimeline(requestedUserName);
            return new ResponseEntity<>(timeline, HttpStatus.OK);
        } catch (TwitterUserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
