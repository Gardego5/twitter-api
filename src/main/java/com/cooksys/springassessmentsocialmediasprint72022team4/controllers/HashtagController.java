package com.cooksys.springassessmentsocialmediasprint72022team4.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping("/{label}")
    public List<TweetResponseDto> getTweetsByLabel(@PathVariable String label) {
        return hashtagService.getTweetsByLabel(label);
    }
}
