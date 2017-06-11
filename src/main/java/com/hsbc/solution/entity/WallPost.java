package com.hsbc.solution.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by seredao on 11.06.17.
 */
@XmlRootElement
public class WallPost {
    private String message;
    private Date postTime;

    public WallPost(String message, Date postTime) {
        this.message = message;
        this.postTime = postTime;
    }

    public WallPost() {
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
