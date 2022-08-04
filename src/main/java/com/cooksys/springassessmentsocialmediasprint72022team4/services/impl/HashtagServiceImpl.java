package com.cooksys.springassessmentsocialmediasprint72022team4.services.impl;

import com.cooksys.springassessmentsocialmediasprint72022team4.repositories.HashtagRepository;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;

    @Override
    public Boolean tagExists(String label) {
        return !hashtagRepository.findByLabelAndDeletedFalse("#" + label).isEmpty();
    }

}
