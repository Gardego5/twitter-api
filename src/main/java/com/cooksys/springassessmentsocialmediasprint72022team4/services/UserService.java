package com.cooksys.springassessmentsocialmediasprint72022team4.services;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
}
