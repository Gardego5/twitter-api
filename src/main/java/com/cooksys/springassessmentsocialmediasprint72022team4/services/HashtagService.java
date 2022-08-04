package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import java.util.List;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;

public interface HashtagService {

    Boolean tagExists(String label);

    List<TweetResponseDto> getTweetsByLabel(String label);
}
