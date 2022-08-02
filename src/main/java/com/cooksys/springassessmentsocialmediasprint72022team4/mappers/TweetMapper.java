package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface TweetMapper {

    Tweet requestDtoToEntity (TweetRequestDto tweetRequestDto);

    @Mapping(target = "author.username", source = "author.credentials.username")
    TweetResponseDto entityToResponseDto (Tweet tweet);

    @Mapping(target = "author.username", source = "author.credentials.username")
    List<TweetResponseDto> entitiesToResponseDtos(List<Tweet> tweets);

    default Long map(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    } 

    default Timestamp map(Long time) {
        return time == null ? null : Timestamp.from(Instant.ofEpochSecond(time));
    }
}
