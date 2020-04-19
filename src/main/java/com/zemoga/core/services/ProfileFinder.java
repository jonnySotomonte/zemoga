package com.zemoga.core.services;

import com.zemoga.core.delivery.rest.model.UpdatableProfileFields;
import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import com.zemoga.core.services.model.Profile;

public interface ProfileFinder {

  Profile findProfileByUserName(String userName) throws PortfolioFinderException, TwitterFinderException;

  void updateProfileByUserName(String userName, UpdatableProfileFields request) throws PortfolioFinderException;
}
