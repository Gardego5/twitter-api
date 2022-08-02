package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;

@Mapper(componentModel = "spring", uses = {TimeMapper.class, UserMapper.class})
public interface TweetMapper {

    Tweet requestDtoToEntity (TweetRequestDto tweetRequestDto);

    TweetResponseDto entityToResponseDto (Tweet tweet);

    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);
}
