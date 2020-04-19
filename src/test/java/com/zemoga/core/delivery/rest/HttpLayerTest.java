package com.zemoga.core.delivery.rest;

import com.zemoga.core.TestZemogaApp;
import com.zemoga.core.services.model.Profile;
import java.util.Objects;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestZemogaApp.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpLayerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void health() {
    ResponseEntity<String> response = restTemplate
        .exchange("/", HttpMethod.GET, null, String.class);
    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assert.assertEquals("Health OK !!!!!", response.getBody());

  }

  @Test
  public void getProfile() {
    ResponseEntity<Profile> response = restTemplate
        .exchange("/profile/Daenerys Targaryen", HttpMethod.GET, null, Profile.class);
    Profile profile = response.getBody();
    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    Assert.assertNotNull(profile);
    Assert.assertEquals("The Mother of Dragons!!", profile.getDescription());
    Assert.assertEquals(
        "https://pbs.twimg.com/profile_images/1117967801652617216/i8PWXebo_400x400.jpg",
        profile.getImageUrl());
    Assert.assertEquals(5, profile.getTweets().size());

  }

  @Test
  public void getProfile_UserNotFound() {
    ResponseEntity<String> response = restTemplate
        .exchange("/profile/Lord Petyr Baelish", HttpMethod.GET, null, String.class);
    Assert.assertEquals(404, response.getStatusCode().value());
    Assert.assertEquals("El usuario no existe", response.getBody());

  }

  @Test
  public void getProfile_TweetsNotFound() {
    ResponseEntity<String> response = restTemplate
        .exchange("/profile/Jonny Esteban Sotomonte", HttpMethod.GET, null, String.class);
    Assert.assertEquals(404, response.getStatusCode().value());
    Assert.assertTrue(Objects.requireNonNull(response.getBody())
        .contains("Hubo un error consultando los tweets del usuario"));

  }

}
