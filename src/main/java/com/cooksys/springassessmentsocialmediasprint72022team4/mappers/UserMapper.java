package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;

@Mapper(componentModel = "spring", uses = {TimeMapper.class})
public interface UserMapper {

    User requestDtoToEntity(UserRequestDto userRequestDto);

    @Mapping(target = "username", source = "credentials.username")
    UserResponseDto entityToResponseDto(User user);

    @Mapping(target = "username", source = "credentials.username")
    List<UserResponseDto> entitiesToResponseDtos(List<User> users);

}
