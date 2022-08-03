package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.BadRequestException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotAuthorizedException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.UserMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.UserRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public User getUser(String username) {
        Optional<User> optionalUser = userRepository.findByCredentials_Username(username);
        if(optionalUser.isEmpty()) {
            throw  new NotFoundException("No User found with username " + username);
        }
        return optionalUser.get();
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User userToCreate = userMapper.requestDtoToEntity(userRequestDto);
        if(userToCreate.getCredentials().getUsername() == null || userToCreate.getCredentials().getPassword() == null || userToCreate.getProfile().getEmail() == null) {
            throw new BadRequestException("All fields are required to create a new account.");
        }

        Optional<User> optionalUser = userRepository.findByCredentials_Username(userToCreate.getCredentials().getUsername());

        if (optionalUser.isEmpty())
            return userMapper.entityToResponseDto(userRepository.saveAndFlush(userToCreate));
        else if (optionalUser.get().isDeleted() && (userToCreate.getCredentials().getPassword().equals(optionalUser.get().getCredentials().getPassword()))) {
            optionalUser.get().setDeleted(false);
            return userMapper.entityToResponseDto(userRepository.saveAndFlush(optionalUser.get()));
        } else if (optionalUser.get().isDeleted() == false) {
            throw new BadRequestException("User already exists.");
        } else
            throw new NotAuthorizedException("User credentials do not match");


    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.entitiesToResponseDtos(userRepository.findAllByDeletedFalse());
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        return userMapper.entityToResponseDto(getUser(username));
    }

}
