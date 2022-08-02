package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;

public interface TweetService {

    TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto);
}
