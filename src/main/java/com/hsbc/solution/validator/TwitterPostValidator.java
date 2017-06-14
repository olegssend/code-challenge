package com.hsbc.solution.validator;

import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.exception.MessageTooLongException;
import com.hsbc.solution.exception.TwitterException;
import org.springframework.stereotype.Component;

/**
 * Created by osereda on 2017-06-13.
 */
@Component
public class TwitterPostValidator implements Validator<TwitterPost> {
    @Override
    public void validate(TwitterPost twitterPost) throws TwitterException {
        if (null == twitterPost.getMessage()) {
            throw new TwitterException("Message can't be null.");
        }
        if (twitterPost.getMessage().length() > 140) {
            throw new MessageTooLongException("Message can't be longer than 140 characters.");
        }
    }
}
