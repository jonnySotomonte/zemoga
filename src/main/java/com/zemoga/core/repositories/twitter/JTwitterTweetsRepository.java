package com.zemoga.core.repositories.twitter;

import com.zemoga.core.exceptions.TwitterFinderException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

@Repository
public class JTwitterTweetsRepository implements TweetsRepository {

  private final Logger logger = LoggerFactory.getLogger(JTwitterTweetsRepository.class);

  private final JTwitterClient client;

  public JTwitterTweetsRepository(JTwitterClient client) {
    this.client = client;
  }

  @Override
  public List<String> findTweetsByUserName(String userName) throws TwitterFinderException {
    logger.info("Consultando los tweets del usuario: {}", userName);
    try {
      int tweetsToReturn = 5;
      ResponseList<Status> timelineTweets = client.getUserTimeline(userName);
      logger.info("Tweets consultados exitosamente");
      return timelineTweets.stream()
          .map(Status::getText)
          .limit(tweetsToReturn)
          .collect(Collectors.toList());

    } catch (TwitterException e) {
      throw new TwitterFinderException(
          "Hubo un error consultando los tweets del usuario: " + userName +
              " causado por: " + e.getMessage());
    }
  }
}
