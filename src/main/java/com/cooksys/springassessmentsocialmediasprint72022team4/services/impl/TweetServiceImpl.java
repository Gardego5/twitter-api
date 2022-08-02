package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.TweetRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.CredentialsService;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final CredentialsService credentialsService;

    @Override
    public TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto) {
        User author = credentialsService.checkAuthorization(tweetRequestDto);
        Tweet tweetToReplyTo = tweetRepository.getOptionalById(id);

        Tweet tweetToPost = tweetMapper.requestDtoToEntity(tweetRequestDto);

        tweetToPost.setAuthor(author);
        tweetToPost.setInReplyTo(tweetToReplyTo);

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
