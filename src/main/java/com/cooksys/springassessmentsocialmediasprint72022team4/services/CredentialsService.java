package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
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
    
}
