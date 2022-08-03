package com.cooksys.springassessmentsocialmediasprint72022team4.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findAllByDeletedFalseAndRepostOf(Tweet tweet);

    Optional<Tweet> findByIdAndDeletedFalse(Integer id);

    default Tweet tryToFindById(Integer id) throws NotFoundException {
        Optional<Tweet> optionalTweet = findByIdAndDeletedFalse(id);

        if (optionalTweet.isEmpty())
            throw new NotFoundException("No Tweet found with id " + id);
        else
            return optionalTweet.get();
    }
}
