package com.zemoga.core.handlers;

import com.zemoga.core.exceptions.PortfolioFinderException;
import com.zemoga.core.exceptions.TwitterFinderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value = {PortfolioFinderException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleCustomExceptions(PortfolioFinderException ex) {
    return ex.getMessage();
  }

  @ExceptionHandler(value = {TwitterFinderException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleCustomExceptionsW(TwitterFinderException ex) {
    return ex.getMessage();
  }

}
