package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import java.util.List;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;

public interface TweetService {

    TweetResponseDto post(TweetRequestDto tweetRequestDto);

    TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto);

    void likeTweet(Integer id, CredentialsDto credentialsDto);

    TweetResponseDto repostTweet(Integer id, CredentialsDto credentialsDto);

    TweetResponseDto getTweetById(Integer id);

    List<UserResponseDto> getTweetMentions(Integer id);

    List<TweetResponseDto> getTweetRetweets(Integer id);

    TweetResponseDto deleteTweetById(Integer id, CredentialsDto credentialsDto);

    List<TweetResponseDto> getTweetReplies(Integer id);
}
