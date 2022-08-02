package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;

public interface TweetService {

    TweetResponseDto post(TweetRequestDto tweetRequestDto);

    TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto);

    TweetResponseDto getTweetById(Integer id);

    TweetResponseDto deleteTweetById(Integer id, CredentialsDto credentialsDto);
}
