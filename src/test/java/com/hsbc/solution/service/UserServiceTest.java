package com.hsbc.solution.service;

import com.hsbc.solution.entity.Post;
import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import com.hsbc.solution.main.Solution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by seredao on 12.06.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Solution.class})
public class UserServiceTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void setUp() throws Exception {
        userService.addPost("firstUser", new TwitterPost(0, "First Post", new Date()));
        userService.addPost("secondUser", new TwitterPost(0, "First Post", new Date()));
        userService.addPost("secondUser", new TwitterPost(0, "Second Post", new Date()));
        userService.addPost("thirdUser", new TwitterPost(0, "First Post", new Date()));
        userService.addPost("thirdUser", new TwitterPost(0, "Second Post", new Date()));
        userService.addPost("thirdUser", new TwitterPost(0, "Third Post", new Date()));
    }

    @After
    public void tearDown() throws Exception {
        userService.cleanup();
    }

    @Test
    public void addPost() throws Exception {
        userService.addPost("fourthUser", new TwitterPost(0, "First Post", new Date()));
        userService.addPost("fourthUser", new TwitterPost(0, "Second Post", new Date()));

        List<Post> wall = userService.getUsersWall("fourthUser");
        assertTrue(wall.size() == 2);
        assertEquals(wall.get(1).getMessage(), "Second Post");
    }

    @Test
    public void addUserToFollowList() throws Exception {
        userService.addUserToFollowList("firstUser", "secondUser");
        userService.addUserToFollowList("firstUser", "thirdUser");

        List<Post> timeline = userService.getUsersTimeline("firstUser");

        assertTrue(timeline.size() == 5);
        assertEquals("Third Post", timeline.get(4).getMessage());
    }

    @Test(expected = TwitterUserNotFoundException.class)
    public void followNonExistentUser() throws Exception {
        userService.addUserToFollowList("firstUser", "aaaaaaaaaaaaaa");
    }

    @Test(expected = TwitterUserNotFoundException.class)
    public void followByNonExistentUser() throws Exception {
        userService.addUserToFollowList("bbbbbbbbbbbbbb", "firstUser");
    }

    @Test
    public void getUsersWall() throws Exception {
        List<Post> firstWall = userService.getUsersWall("firstUser");
        List<Post> secondWall = userService.getUsersWall("secondUser");
        List<Post> thirdWall = userService.getUsersWall("thirdUser");

        assertTrue(firstWall.size() == 1);
        assertTrue(secondWall.size() == 2);
        assertTrue(thirdWall.size() == 3);
        assertEquals("Third Post", thirdWall.get(2).getMessage());
    }

    @Test(expected = TwitterUserNotFoundException.class)
    public void getNonExistentWall() throws Exception {
        userService.getUsersWall("ccccccccccccccc");
    }

    @Test
    public void getUsersTimeline() throws Exception {
        userService.addPost("fourthUser", new TwitterPost(0, "Third Post", new Date()));
        userService.addUserToFollowList("fourthUser", "firstUser");
        userService.addUserToFollowList("fourthUser", "secondUser");
        userService.addUserToFollowList("fourthUser", "thirdUser");

        List<Post> firstTimeline = userService.getUsersTimeline("firstUser");
        List<Post> fourthTimeline = userService.getUsersTimeline("fourthUser");

        System.out.println(fourthTimeline);

        assertTrue(firstTimeline.size() == 0);
        assertTrue(fourthTimeline.size() == 6);
    }

}