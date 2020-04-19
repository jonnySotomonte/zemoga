package com.zemoga.core.services;

import com.zemoga.core.delivery.rest.model.UpdatableProfileFields;
import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import com.zemoga.core.repositories.db.model.Portfolio;
import com.zemoga.core.repositories.db.PortfolioRepository;
import com.zemoga.core.repositories.twitter.TweetsRepository;
import com.zemoga.core.services.model.Profile;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultProfileFinder implements ProfileFinder {

  private final Logger logger = LoggerFactory.getLogger(DefaultProfileFinder.class);

  private final PortfolioRepository repository;
  private final TweetsRepository tweetsRepository;

  public DefaultProfileFinder(PortfolioRepository repository,
      TweetsRepository tweetsRepository) {
    this.repository = repository;
    this.tweetsRepository = tweetsRepository;
  }

  @Override
  public Profile findProfileByUserName(String userName)
      throws PortfolioFinderException, TwitterFinderException {
    logger.info("Consultando el peril del usuario: {}", userName);
    Portfolio portfolio = getPortfolio(userName);
    List<String> tweets = getTweets(portfolio.getTwitterUserName());
    return buildProfile(portfolio, tweets);
  }

  @Override
  public void updateProfileByUserName(String userName, UpdatableProfileFields request) throws PortfolioFinderException {
    Portfolio portfolio = getPortfolio(userName);
    updateProfile(portfolio, request);
  }

  private Portfolio getPortfolio(String userName) throws PortfolioFinderException {
    logger.info("Consultando informacion de portafolio del usuario: {}", userName);
    Optional<Portfolio> optPortfolio = repository.findByTitle(userName);
    if (optPortfolio.isPresent()) {
      logger.info("Informacion de portafolio del usuario: {} consultada exitosamente", userName);
      return optPortfolio.get();
    } else {
      throw new PortfolioFinderException("El usuario no existe");
    }
  }

  private List<String> getTweets(String twitterUserName) throws TwitterFinderException {
    try {
      return tweetsRepository.findTweetsByUserName(twitterUserName);
    } catch (TwitterFinderException e) {
      logger.info(e.getMessage());
      throw e;
    }
  }

  private Profile buildProfile(Portfolio portfolio, List<String> tweets) {
    return new Profile(portfolio.getTitle(),
        portfolio.getImageUrl(),
        portfolio.getDescription(),
        tweets);
  }

  private void updateProfile(Portfolio portfolio, UpdatableProfileFields request) {
    logger.info("Actualizando perfil del usuario", portfolio.getTitle());
    portfolio.setDescription(request.getDescription());
    repository.save(portfolio);
    logger.info("Perfil actualziado exitosamente");
  }
}
