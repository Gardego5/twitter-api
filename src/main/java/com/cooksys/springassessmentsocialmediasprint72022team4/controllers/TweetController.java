package com.cooksys.springassessmentsocialmediasprint72022team4.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.TweetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto post(@RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.post(tweetRequestDto);
    }

    @PostMapping("/{id}/reply")
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto postReply(@PathVariable Integer id, @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.postReply(id, tweetRequestDto);
    }

    @PostMapping("/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void likeTweet(@PathVariable Integer id, @RequestBody CredentialsDto credentialsDto) {
        tweetService.likeTweet(id, credentialsDto);
    }

    @PostMapping("/{id}/repost")
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto repostTweet(@PathVariable Integer id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.repostTweet(id, credentialsDto);
    }

    @GetMapping("/{id}")
    public TweetResponseDto getTweetById(@PathVariable Integer id) {
        return tweetService.getTweetById(id);
    }

    @GetMapping("/{id}/mentions")
    public List<UserResponseDto> getTweetMentions(@PathVariable Integer id) {
        return tweetService.getTweetMentions(id);
    }

    @GetMapping("/{id}/reposts")
    public List<TweetResponseDto> getTweetRetweets(@PathVariable Integer id) {
        return tweetService.getTweetRetweets(id);
    }

    @DeleteMapping("/{id}")
    public TweetResponseDto deleteTweetById(@PathVariable Integer id, @RequestBody CredentialsDto credentialsDto) {
        return tweetService.deleteTweetById(id, credentialsDto);
    }
}
