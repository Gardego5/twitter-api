package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;

public interface CredentialsService {

    /**
     * Verifies that the credentials in a dto correspond with a user and
     * that the password is correct.
     * 
     * @param tweetRequestDto
     * @return {@code User} The user that matched the credentials in the dto.
     */
    User checkAuthorization(TweetRequestDto tweetRequestDto);

    /**
     * Verifies that the credentials in the dto correspond with the user
     * who made the tweet, and that their password is correct.
     * 
     * @param credentialsDto
     * @param tweetToDelete
     * @return {@code User} The user that matched the credentials in the dto.
     */
    User checkAuthorization(CredentialsDto credentialsDto, Tweet tweetToDelete);

    /**
     * Verifies that the credentials in the dto correspond with a user
     * and returns the user.
     * @param credentialsDto
     * @return {@code User} The user whose credentials match the ones provided
     */
    User checkAuthorization(CredentialsDto credentialsDto);

    User checkAuthorization(CredentialsDto credentialsDto, String username);
    
}
