package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Profile;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.ProfileDto;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile dtoToEntity(ProfileDto profileDto);

    ProfileDto entityToDto(Profile profile);

    List<ProfileDto> entitiesToDtos(List<Profile> profiles);

}
