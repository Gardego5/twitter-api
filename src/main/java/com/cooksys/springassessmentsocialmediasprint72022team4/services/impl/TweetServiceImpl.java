package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.TweetRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.UserRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.CredentialsService;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final UserRepository userRepository;
    private final CredentialsService credentialsService;

    private List<User> findMentions(String content) {
        List<User> mentions = new ArrayList<>();
        Matcher m = Pattern.compile("@[^\\s]+").matcher(content);
        
        while (m.find()) {
            try {
                // If the user exists, add them to the mentions list.
                mentions.add(userRepository.getOptionalByUsername(m.group().substring(1)));
            } catch (NotFoundException e) {
                // Do nothing, if the user doesn't exist.
            }
        }

        return mentions;
    }

    @Override
    public TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto) {
        User author = credentialsService.checkAuthorization(tweetRequestDto);
        Tweet tweetToReplyTo = tweetRepository.getOptionalById(id);

        Tweet tweetToPost = tweetMapper.requestDtoToEntity(tweetRequestDto);

        tweetToPost.setAuthor(author);
        tweetToPost.setInReplyTo(tweetToReplyTo);
        tweetToPost.setMentions(findMentions(tweetRequestDto.getContent()));

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToPost));
    }

    @Override
    public TweetResponseDto getTweetById(Integer id) {
        return tweetMapper.entityToResponseDto(
            tweetRepository.getOptionalById(id));
    }

    @Override
    public TweetResponseDto deleteTweetById(Integer id, CredentialsDto credentialsDto) {
        Tweet tweetToDelete = tweetRepository.getOptionalById(id);
        credentialsService.checkAuthorization(credentialsDto, tweetToDelete);

        tweetToDelete.setDeleted(true);

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToDelete));
    }
}
