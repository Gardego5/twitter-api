package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Credentials;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotAuthorizedException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.CredentialsMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.UserRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.CredentialsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserRepository userRepository;

    private User checkAuthorization(Credentials credentials) {
        User user = userRepository.getOptionalByUsername(credentials.getUsername());
        if (user.getCredentials().equals(credentials))
            return user;
        else
            throw new NotAuthorizedException("Invalid Password.");
    }

    private User checkAuthorization(CredentialsDto credentialsDto) {
        return checkAuthorization(credentialsMapper.dtoToEntity(credentialsDto));
    }

    @Override
    public User checkAuthorization(TweetRequestDto tweetRequestDto) {
        return checkAuthorization(tweetRequestDto.getCredentials());
    }
}
