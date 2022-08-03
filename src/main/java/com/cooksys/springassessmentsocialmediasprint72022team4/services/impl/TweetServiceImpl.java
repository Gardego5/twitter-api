package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Hashtag;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.BadRequestException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.UserMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
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
    private final UserMapper userMapper;
    private final HashtagRepository hashtagRepository;
    private final CredentialsService credentialsService;

    private Set<User> findMentions(String content) {
        Set<User> mentions = new HashSet<>();
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

    private Set<Hashtag> findHashtags(String content) {
        Set<Hashtag> hashtags = new HashSet<>();
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
    public void likeTweet(Integer id, CredentialsDto credentialsDto) {
        Tweet tweetToLike = tweetRepository.tryToFindById(id);
        User user = credentialsService.checkAuthorization(credentialsDto);

        Set<Tweet> likedTweets = user.getLikedTweets();
        likedTweets.add(tweetToLike);

        user.setLikedTweets(likedTweets);
        userRepository.saveAndFlush(user);
    }

    @Override
    public TweetResponseDto repostTweet(Integer id, CredentialsDto credentialsDto) {
        Tweet originalTweet = tweetRepository.tryToFindById(id);
        User author = credentialsService.checkAuthorization(credentialsDto);

        Tweet tweetToRepost = new Tweet();
        tweetToRepost.setAuthor(author);
        tweetToRepost.setRepostOf(originalTweet);
        tweetToRepost.setContent(originalTweet.getContent());

        /*
         * Note: Using Set.copyOf() to make a new reference to the set,
         * so that we dont get an error stating "Found shared references
         * to a collection"
         */
        tweetToRepost.setMentions(
            Set.copyOf(originalTweet.getMentions()));
        tweetToRepost.setHashtags(
            Set.copyOf(originalTweet.getHashtags()));

        return tweetMapper.entityToResponseDto(
            tweetRepository.saveAndFlush(tweetToRepost));
    }

    @Override
    public TweetResponseDto getTweetById(Integer id) {
        return tweetMapper.entityToResponseDto(
            tweetRepository.tryToFindById(id));
    }

    @Override
    public List<UserResponseDto> getTweetMentions(Integer id) {
        Tweet tweet = tweetRepository.tryToFindById(id);
        return userMapper.entitiesToResponseDtos(tweet.getMentions());
    }

    @Override
    public List<TweetResponseDto> getTweetRetweets(Integer id) {
        Tweet tweet = tweetRepository.tryToFindById(id);
        return tweetMapper.entitiesToResponseDtos(
            tweetRepository.findAllByDeletedFalseAndRepostOf(tweet));
    }

    @Override
    public List<TweetResponseDto> getTweetReplies(Integer id) {
        Tweet tweet = tweetRepository.tryToFindById(id);
        return tweetMapper.entitiesToResponseDtos(
            tweetRepository.findAllByDeletedFalseAndInReplyTo(tweet));
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
