package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Hashtag;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.BadRequestException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.HashtagRepository;
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
    private final HashtagRepository hashtagRepository;
    private final CredentialsService credentialsService;

    private List<User> findMentions(String content) {
        List<User> mentions = new ArrayList<>();
        Matcher m = Pattern.compile("@[^\\s]+").matcher(content);
        String username = "";
        
        while (m.find()) {
            // Drop the @ symbol from beginning of mention matched.
            username = m.group().substring(1);

            try {
                // If the user exists, add them to the mentions list.
                mentions.add(userRepository.tryToFindByUsername(username));
            } catch (NotFoundException e) {
                // Do nothing, if the user doesn't exist.
            }
        }

        return mentions;
    }

    private List<Hashtag> findHashtags(String content) {
        List<Hashtag> hashtags = new ArrayList<>();
        Matcher m = Pattern.compile("#[^\\s]+").matcher(content);
        String label = "";

        while (m.find()) {
            // Drop the # symbol from beginning of hashtag match.
            label = m.group();

            try {
                hashtags.add(hashtagRepository.tryToFindByLabel(label));
            } catch (NotFoundException e) {
                // Create a new hashtag if one isn't found.
                Hashtag newHashtag = new Hashtag();
                newHashtag.setLabel(label);
                hashtags.add(newHashtag);
            }
        }

        hashtagRepository.saveAllAndFlush(hashtags);
        return hashtags;
    }

    private Tweet setupTweet(TweetRequestDto tweetRequestDto) {
        User author = credentialsService.checkAuthorization(tweetRequestDto);
        Tweet tweet = tweetMapper.requestDtoToEntity(tweetRequestDto);

        if (tweet.getContent() == null || tweet.getContent() == "")
            throw new BadRequestException("Tweet has no content");

        tweet.setAuthor(author);
        tweet.setMentions(findMentions(tweetRequestDto.getContent()));
        tweet.setHashtags(findHashtags(tweetRequestDto.getContent()));

        return tweet;
    }

    @Override
    public TweetResponseDto post(TweetRequestDto tweetRequestDto) {
        Tweet tweetToPost = setupTweet(tweetRequestDto);

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToPost));
    }

    @Override
    public TweetResponseDto postReply(Integer id, TweetRequestDto tweetRequestDto) {
        Tweet tweetToPost = setupTweet(tweetRequestDto);
        Tweet tweetToReplyTo = tweetRepository.tryToFindById(id);

        tweetToPost.setInReplyTo(tweetToReplyTo);

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToPost));
    }

    @Override
    public TweetResponseDto getTweetById(Integer id) {
        return tweetMapper.entityToResponseDto(
            tweetRepository.tryToFindById(id));
    }

    @Override
    public TweetResponseDto deleteTweetById(Integer id, CredentialsDto credentialsDto) {
        Tweet tweetToDelete = tweetRepository.tryToFindById(id);
        credentialsService.checkAuthorization(credentialsDto, tweetToDelete);

        tweetToDelete.setDeleted(true);

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToDelete));
    }
}
