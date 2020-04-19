package com.zemoga.core.delivery.rest;

import com.zemoga.core.delivery.rest.model.UpdatableProfileFields;
import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import com.zemoga.core.services.model.Profile;
import com.zemoga.core.services.ProfileFinder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultProfileController {

  private final ProfileFinder profileFinder;

  public DefaultProfileController(ProfileFinder profileFinder) {
    this.profileFinder = profileFinder;
  }

  @GetMapping("/")
  public ResponseEntity<String> health() {
    return new ResponseEntity<>("Health OK !!!!!", HttpStatus.OK);
  }

  @GetMapping("/profile/{userName}")
  public ResponseEntity<Profile> getProfile(@PathVariable String userName)
      throws PortfolioFinderException, TwitterFinderException {
    Profile profile = profileFinder.findProfileByUserName(userName);
    return new ResponseEntity<>(profile, HttpStatus.OK);
  }

  @PutMapping("/profile/{userName}")
  public ResponseEntity<Void> updateProfile(@PathVariable String userName,
      @RequestBody UpdatableProfileFields request) throws Exception {
    profileFinder.updateProfileByUserName(userName, request);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
