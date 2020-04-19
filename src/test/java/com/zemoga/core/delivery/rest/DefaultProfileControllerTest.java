package com.zemoga.core.delivery.rest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.zemoga.core.delivery.rest.model.UpdatableProfileFields;
import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import com.zemoga.core.services.model.Profile;
import com.zemoga.core.services.ProfileFinder;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProfileControllerTest {

  private DefaultProfileController controller;

  @Mock
  private ProfileFinder profileFinder;

  @Before
  public void setup() {
    controller = new DefaultProfileController(profileFinder);
  }

  @Test
  public void health() {
    ResponseEntity<String> response = controller.health();
    String message = response.getBody();
    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assert.assertEquals("Health OK !!!!!", message);
  }

  @Test
  public void getProfile() throws TwitterFinderException, PortfolioFinderException {
    when(profileFinder.findProfileByUserName(anyString())).thenReturn(buildProfile());
    ResponseEntity<Profile> response = controller.getProfile("Jhon Doe");
    Profile profile = response.getBody();
    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assert.assertNotNull(profile);
    Assert.assertEquals(2, profile.getTweets().size());
  }

  @Test(expected = PortfolioFinderException.class)
  public void getProfile_UserNotFound() throws TwitterFinderException, PortfolioFinderException {
    when(profileFinder.findProfileByUserName(anyString())).thenThrow(PortfolioFinderException.class);
    controller.getProfile("Jhon Doe");
  }

  @Test(expected = TwitterFinderException.class)
  public void getProfile_TweetsNotFound() throws TwitterFinderException, PortfolioFinderException {
    when(profileFinder.findProfileByUserName(anyString())).thenThrow(TwitterFinderException.class);
    controller.getProfile("Jhon Doe");
  }

  @Test
  public void updateProfile() throws Exception {
    ResponseEntity<Void> response = controller.updateProfile("Jhon Doe", buildUpdateRequest());
    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
  }

  private Profile buildProfile() {
    List<String> tweets = Arrays.asList("tweet One", "tweet Two");
    return new Profile("Jhon Doe", "http://image.com", "description", tweets);
  }

  private UpdatableProfileFields buildUpdateRequest() {
    UpdatableProfileFields request = new UpdatableProfileFields();
    request.setDescription("new description");
    return request;
  }

}
