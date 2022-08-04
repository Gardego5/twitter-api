package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cooksys.springassessmentsocialmediasprint72022team4.mappers.TweetMapper;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.HashtagRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.TweetRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final TweetMapper tweetMapper;
    private final TweetRepository tweetRepository;

    @Override
    public Boolean tagExists(String label) {
        return !hashtagRepository.findByLabelAndDeletedFalse(String.join("", List.of("#", label))  ).isEmpty();
    }

    @Override
    public List<TweetResponseDto> getTweetsByLabel(String label) {
        Integer hashtag_id = hashtagRepository.tryToFindByLabel("#" + label).getId();
        return tweetMapper.entitiesToResponseDtos(
            tweetRepository.getByHashtags(hashtag_id, Sort.by(Sort.Direction.DESC, "posted")));
    }
}
