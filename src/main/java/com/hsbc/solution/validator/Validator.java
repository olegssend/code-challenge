package com.hsbc.solution.validator;

import com.hsbc.solution.exception.TwitterException;

/**
 * Created by osereda on 2017-06-13.
 */
public interface Validator<T> {
    void validate(T t) throws TwitterException;
}
