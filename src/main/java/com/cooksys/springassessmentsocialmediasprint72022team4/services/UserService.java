package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllUsers();

    UserResponseDto getUserByUsername(String username);

    User getUser(String username);

    Boolean usernameExists(String username);

    Boolean usernameAvailable(String username);

    UserResponseDto deleteUser(String username, CredentialsDto credentialsDto);

    List<TweetResponseDto> getUserFeed(String username);

    UserResponseDto updateProfile(String username, UserRequestDto userRequestDto);
}
