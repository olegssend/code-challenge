package com.hsbc.solution.validator;

import com.hsbc.solution.exception.MessageTooLongException;
import com.hsbc.solution.exception.TwitterException;

/**
 * Created by osereda on 2017-06-13.
 */
public class MessageValidator implements Validator<String> {
    @Override
    public void validate(String message) throws TwitterException {
        if (message.length() > 140) {
            throw new MessageTooLongException("Message can't be longer than 140 characters.");
        }
    }
}
