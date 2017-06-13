package com.hsbc.solution.validator;

import com.hsbc.solution.exception.TwitterException;
import org.springframework.stereotype.Component;

/**
 * Created by osereda on 2017-06-13.
 */
@Component
public interface Validator<T> {
    void validate(T t) throws TwitterException;
}
