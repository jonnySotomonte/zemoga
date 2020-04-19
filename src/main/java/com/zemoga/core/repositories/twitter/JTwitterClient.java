package com.zemoga.core.repositories.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Component
public class JTwitterClient {

  private final String TWITTER_CONSUMER_KEY;
  private final String TWITTER_SECRET_KEY;
  private final String TWITTER_ACCESS_TOKEN;
  private final String TWITTER_ACCESS_TOKEN_SECRET;

  private final Twitter twitterClient;

  public JTwitterClient(@Value("${twitter.api.key}") String apiKey,
      @Value("${twitter.secret.key}") String secretKey,
      @Value("${twitter.access.token}") String accessToken,
      @Value("${twitter.access.token.secret}") String accessTokenSecret) {
    TWITTER_CONSUMER_KEY = apiKey;
    TWITTER_SECRET_KEY = secretKey;
    TWITTER_ACCESS_TOKEN = accessToken;
    TWITTER_ACCESS_TOKEN_SECRET = accessTokenSecret;
    twitterClient = buildTwitterClient();
  }

  public ResponseList<Status> getUserTimeline(String userName) throws TwitterException {
    return twitterClient.getUserTimeline(userName);
  }

  private Twitter buildTwitterClient() {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
        .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
        .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
        .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
        .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
    TwitterFactory tf = new TwitterFactory(cb.build());
    return tf.getInstance();
  }

}
