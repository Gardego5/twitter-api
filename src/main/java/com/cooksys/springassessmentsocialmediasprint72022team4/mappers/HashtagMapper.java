package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Hashtag;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.HashtagDto;

@Mapper(componentModel = "spring", uses = {TimeMapper.class})
public interface HashtagMapper {

    Hashtag dtoToEntity(HashtagDto hashtagDto);

    HashtagDto entityToDto(Hashtag hashtag);

    List<HashtagDto> entitiesToDtos( List<Hashtag> hashtags);
}
