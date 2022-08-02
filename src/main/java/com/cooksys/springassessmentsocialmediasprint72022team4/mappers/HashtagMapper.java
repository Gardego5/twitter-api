package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Hashtag;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.HashtagDto;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

    Hashtag dtoToEntity(HashtagDto hashtagDto);

    HashtagDto entityToDto(Hashtag hashtag);

    List<HashtagDto> entitiesToDtos( List<Hashtag> hashtags);

    default Long map(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    } 

    default Timestamp map(Long time) {
        return time == null ? null : Timestamp.from(Instant.ofEpochSecond(time));
    }
}
