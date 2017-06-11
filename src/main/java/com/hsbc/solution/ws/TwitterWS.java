package com.hsbc.solution.ws;

import com.hsbc.solution.entity.WallPost;
import com.hsbc.solution.exception.TwitterUserNotFoundException;
import com.hsbc.solution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by seredao on 11.06.17.
 */
@Component
@Path("/")
@Produces({MediaType.APPLICATION_XML})
public class TwitterWS {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response postMessage(String message,
                                @HeaderParam("userId") String requestedUserName) {

        userService.addPost(requestedUserName, message);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("follow/{userNameToFollow}")
    public Response followUser(@PathParam("userNameToFollow") String userNameToFollow,
                               @HeaderParam("userId") String requestedUserName) {

        try {
            userService.addUserToFollowList(requestedUserName, userNameToFollow);
            return Response.ok().build();
        } catch (TwitterUserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("wall")
    public Response getWall(@HeaderParam("userId") String requestedUserName) {

        try {
            List<WallPost> wall = userService.getUsersWall(requestedUserName);
            GenericEntity<List<WallPost>> entity = new GenericEntity<List<WallPost>>(wall) {
            };
            return Response.ok(entity).build();
        } catch (TwitterUserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("timeline")
    public Response getTimeline(@HeaderParam("userId") String requestedUserName) {

        try {
            List<WallPost> timeline = userService.getUsersTimeline(requestedUserName);
            GenericEntity<List<WallPost>> entity = new GenericEntity<List<WallPost>>(timeline) {
            };
            return Response.ok(entity).build();
        } catch (TwitterUserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
