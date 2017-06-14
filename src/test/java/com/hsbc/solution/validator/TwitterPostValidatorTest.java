package com.hsbc.solution.validator;

import com.hsbc.solution.entity.TwitterPost;
import com.hsbc.solution.exception.MessageTooLongException;
import com.hsbc.solution.exception.TwitterException;
import com.hsbc.solution.main.Solution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by osereda on 2017-06-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Solution.class})
public class TwitterPostValidatorTest {

    private TwitterPostValidator validator;

    @Autowired
    public void setValidator(TwitterPostValidator validator) {
        this.validator = validator;
    }

    @Test(expected = MessageTooLongException.class)
    public void testMessageLengthValidation() throws Exception {
        TwitterPost twitterPost = new TwitterPost(0
                , "PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_" +
                "PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_" +
                "PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_" +
                "PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_PRETTY_LONG_MESSAGE_"
                , new Date());
        validator.validate(twitterPost);
    }

    @Test(expected = TwitterException.class)
    public void testMessageNotNullValidation() throws Exception {
        TwitterPost twitterPost = new TwitterPost(0, null, new Date());
        validator.validate(twitterPost);
    }
}