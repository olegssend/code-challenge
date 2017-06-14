package com.hsbc.solution.entity;

import java.util.Date;

/**
 * Created by seredao on 11.06.17.
 */
public class TwitterPost implements Post {

    private Integer id;
    private String message;
    private Date postTime;

    public TwitterPost(Integer id, String message, Date postTime) {
        this.id = id;
        this.message = message;
        this.postTime = postTime;
    }

    public TwitterPost() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
