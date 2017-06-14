package com.hsbc.solution.entity;

import java.util.Date;

/**
 * Created by osereda on 2017-06-13.
 */
public interface Post {
    Integer getId();

    void setId(Integer id);

    String getMessage();

    void setMessage(String message);

    Date getPostTime();

    void setPostTime(Date postTime);
}
