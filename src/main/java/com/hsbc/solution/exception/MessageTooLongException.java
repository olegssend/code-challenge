package com.hsbc.solution.exception;

/**
 * Created by osereda on 2017-06-13.
 */
public class MessageTooLongException extends TwitterException {
    public MessageTooLongException(String message) {
        super(message);
    }
}
