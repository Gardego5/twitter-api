package com.cooksys.springassessmentsocialmediasprint72022team4.mappers;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CredentialsMapper.class, ProfileMapper.class})
public interface UserMapper {

    User requestDtoToEntity (UserRequestDto userRequestDto);

//    UserResponseDto entityToResponseDto (User user);

//    List<UserResponseDto> entitiesToResponseDtos(List<User> users);

}
