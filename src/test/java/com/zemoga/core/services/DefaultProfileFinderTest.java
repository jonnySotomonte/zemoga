package com.zemoga.core.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.zemoga.core.delivery.rest.model.UpdatableProfileFields;
import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import com.zemoga.core.repositories.db.PortfolioRepository;
import com.zemoga.core.repositories.db.model.Portfolio;
import com.zemoga.core.repositories.twitter.TweetsRepository;
import com.zemoga.core.services.model.Profile;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProfileFinderTest {

  private DefaultProfileFinder profileFinder;

  @Mock
  private PortfolioRepository repository;

  @Mock
  private TweetsRepository tweetsRepository;

  @Before
  public void setup() {
    profileFinder = new DefaultProfileFinder(repository, tweetsRepository);
  }

  @Test
  public void findProfileByUserName() throws TwitterFinderException, PortfolioFinderException {
    when(repository.findByTitle(anyString())).thenReturn(buildPortfolio());
    when(tweetsRepository.findTweetsByUserName(anyString())).thenReturn(buildTweets());
    Profile profile = profileFinder.findProfileByUserName("Jhon Doe");
    Assert.assertNotNull(profile);
    Assert.assertEquals(profile.getDescription(), "description");
    Assert.assertEquals(profile.getImageUrl(), "http://image.com");
    Assert.assertEquals(profile.getUserName(), "Jhon Doe");
    Assert.assertEquals(profile.getTweets().size(), 3);
  }

  @Test(expected = PortfolioFinderException.class)
  public void findProfileByUserName_UserNotFound()
      throws TwitterFinderException, PortfolioFinderException {
    when(repository.findByTitle(anyString())).thenReturn(Optional.empty());
    profileFinder.findProfileByUserName("Jhon Doe");
  }

  @Test(expected = TwitterFinderException.class)
  public void findProfileByUserName_TweetsNotFounds() throws TwitterFinderException, PortfolioFinderException {
    when(repository.findByTitle(anyString())).thenReturn(buildPortfolio());
    when(tweetsRepository.findTweetsByUserName(anyString())).thenThrow(TwitterFinderException.class);
    profileFinder.findProfileByUserName("Jhon Doe");
  }

  @Test
  public void updateProfileByUserName() throws PortfolioFinderException {
    when(repository.findByTitle(anyString())).thenReturn(buildPortfolio());
    profileFinder.updateProfileByUserName("Jhon Doe", buildUpdateRequest());
  }

  @Test(expected = PortfolioFinderException.class)
  public void updateProfileByUserName_UserNotFound() throws PortfolioFinderException {
    when(repository.findByTitle(anyString())).thenReturn(Optional.empty());
    profileFinder.updateProfileByUserName("Jhon Doe", buildUpdateRequest());
  }

  private Optional<Portfolio> buildPortfolio() {
    Portfolio portfolio = new Portfolio(1, "description", "http://image.com", "JhonDoe24",
        "Jhon Doe");
    return Optional.of(portfolio);
  }

  private List<String> buildTweets() {
    return Arrays.asList("tweet One", "tweet Two", "tweet Three");
  }

  private UpdatableProfileFields buildUpdateRequest() {
    UpdatableProfileFields request = new UpdatableProfileFields();
    request.setDescription("new description");
    return request;
  }

}
