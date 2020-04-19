package com.zemoga.core.repositories.twitter;

import com.zemoga.core.exceptions.TwitterFinderException;
import java.util.List;
import twitter4j.TwitterException;

public interface TweetsRepository {

  List<String> findTweetsByUserName(String userName) throws TwitterFinderException;

}
