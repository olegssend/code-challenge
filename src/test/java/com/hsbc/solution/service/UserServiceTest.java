package com.hsbc.solution.service;

import com.hsbc.solution.entity.WallPost;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by seredao on 12.06.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    private static boolean isSetupDone = false;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void setUp() throws Exception {
        if (!isSetupDone) {
            userService.addPost("firstUser", "First Post");
            userService.addPost("secondUser", "First Post");
            userService.addPost("secondUser", "Second Post");
            userService.addPost("thirdUser", "First Post");
            userService.addPost("thirdUser", "Second Post");
            userService.addPost("thirdUser", "Third Post");
        }
        isSetupDone = true;
    }

    @Test
    public void addPost() throws Exception {
        userService.addPost("fourthUser", "First Post");
        userService.addPost("fourthUser", "Pretty_Long_Post_Pretty_Long_Post_" +
                "Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_" +
                "Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_" +
                "Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_" +
                "Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_Pretty_Long_Post_");

        List<WallPost> wall = userService.getUsersWall("fourthUser");

        assertTrue(wall.size() == 2);
        assertTrue(wall.get(1).getMessage().length() == 140);
        assertEquals(wall.get(0).getMessage(), "First Post");
    }


    @Test
    public void addUserToFollowList() throws Exception {
        userService.addUserToFollowList("firstUser", "secondUser");
        userService.addUserToFollowList("firstUser", "thirdUser");

        List<WallPost> timeline = userService.getUsersTimeline("firstUser");

        assertTrue(timeline.size() == 5);
        assertEquals(timeline.get(4).getMessage(), "Third Post");
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
        List<WallPost> firstWall = userService.getUsersWall("firstUser");
        List<WallPost> secondWall = userService.getUsersWall("secondUser");
        List<WallPost> thirdWall = userService.getUsersWall("thirdUser");

        assertTrue(firstWall.size() == 1);
        assertTrue(secondWall.size() == 2);
        assertTrue(thirdWall.size() == 3);
        assertEquals(thirdWall.get(2).getMessage(), "Third Post");
    }

    @Test(expected = TwitterUserNotFoundException.class)
    public void getNonExistentWall() throws Exception {
        userService.getUsersWall("ccccccccccccccc");
    }

    @Test
    public void getUsersTimeline() throws Exception {
        userService.addPost("fourthUser", "First Post");
        userService.addUserToFollowList("fourthUser", "firstUser");
        userService.addUserToFollowList("fourthUser", "secondUser");
        userService.addUserToFollowList("fourthUser", "thirdUser");

        List<WallPost> firstTimeline = userService.getUsersTimeline("firstUser");
        List<WallPost> fourthTimeline = userService.getUsersTimeline("fourthUser");

        System.out.println(fourthTimeline);

        assertTrue(firstTimeline.size() == 0);
        assertTrue(fourthTimeline.size() == 6);

    }

}