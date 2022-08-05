package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Credentials;
import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.BadRequestException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotAuthorizedException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.UserMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.TweetRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.UserRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.CredentialsService;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final CredentialsService credentialsService;

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

    @Override
    public List<TweetResponseDto> getUserFeed(String username) {
        User user = userRepository.tryToFindByUsername(username);
        List<Tweet> feed = new ArrayList<>();
        feed.addAll(tweetRepository.findAllByDeletedFalseAndAuthor(user));

        user.getFollowing().forEach(followedUser -> {
            feed.addAll(tweetRepository.findAllByDeletedFalseAndAuthor(followedUser));
        });
        
        feed.sort((t1, t2) -> Long.valueOf(t2.getPosted().getTime()).compareTo(t1.getPosted().getTime()));

        return tweetMapper.entitiesToResponseDtos(feed);
    }

    @Override
    public void followUser(String username, CredentialsDto credentialsDto) {
        User userToFollow = getUser(username);
        User userFollowing = credentialsService.checkAuthorization(credentialsDto);
        if (userFollowing.getFollowing().contains(userToFollow)) {
            throw new BadRequestException("You are following this user already!");
        }
        userFollowing.getFollowing().add(userToFollow);
        userMapper.entityToResponseDto(userRepository.saveAndFlush(userFollowing));
    }

    @Override
    public void unfollowUser(String username, CredentialsDto credentialsDto) {
        User userToFollow = getUser(username);
        User userFollowing = credentialsService.checkAuthorization(credentialsDto);
        if (!userFollowing.getFollowing().contains(userToFollow)) {
            throw new BadRequestException("You are NOT following this user.");
        }
        userFollowing.getFollowing().remove(userToFollow);
        userMapper.entityToResponseDto(userRepository.saveAndFlush(userFollowing));
    }

    @Override
    public Boolean usernameExists(String username) {
        return !userRepository.findByCredentials_UsernameAndDeletedFalse(username).isEmpty();
    }

    @Override
    public Boolean usernameAvailable(String username) {
        return userRepository.findByCredentials_Username(username).isEmpty();
    }

    @Override
    public UserResponseDto deleteUser(String username, CredentialsDto credentialsDto) {
        User user = credentialsService.checkAuthorization(credentialsDto, username);
        user.setDeleted(true);
        user.getTweets().forEach(tweet -> tweet.setDeleted(true));

        return userMapper.entityToResponseDto(userRepository.saveAndFlush(user));
    }

}
