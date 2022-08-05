package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;
import java.util.Set;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Credentials;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;

@Mapper(componentModel = "spring", uses = {TimeMapper.class})
public interface UserMapper {

    Credentials dtoToCredentialsEntity(CredentialsDto credentialsDto);

    User requestDtoToEntity(UserRequestDto userRequestDto);

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToResponseDto(User user);

    @Mapping(target = "username", source = "credentials.username")
    List<UserResponseDto> entitiesToResponseDtos(List<User> users);

    @Mapping(target = "username", source = "credentials.username")
    List<UserResponseDto> entitiesToResponseDtos(Set<User> users);

}
