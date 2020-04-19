package com.zemoga.core.services.model;

import java.util.List;

public class Profile {

  private final String userName;
  private final String imageUrl;
  private final String description;
  private final List<String> tweets;

  public Profile(String userName, String imageUrl, String description,
      List<String> tweets) {
    this.userName = userName;
    this.imageUrl = imageUrl;
    this.description = description;
    this.tweets = tweets;
  }

  public String getUserName() {
    return userName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public List<String> getTweets() {
    return tweets;
  }
}
