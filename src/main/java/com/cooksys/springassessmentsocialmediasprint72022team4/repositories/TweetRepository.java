package com.cooksys.springassessmentsocialmediasprint72022team4.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Tweet;
import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findAllByDeletedFalse(Sort sort);

    List<Tweet> findAllByDeletedFalseAndRepostOf(Tweet tweet);

    List<Tweet> findAllByDeletedFalseAndInReplyTo(Tweet tweet);

    @Query("SELECT t FROM Tweet t INNER JOIN t.hashtags h WHERE h.id = :hashtag_id")
    List<Tweet> getByHashtags(@Param("hashtag_id") Integer hashtag_id, Sort sort);

    @Query("SELECT t FROM Tweet t INNER JOIN t.mentions m WHERE m.id = :user_id")
    List<Tweet> getByUserMention(@Param("user_id") Integer user_id);

    List<Tweet> findAllByDeletedFalseAndAuthor(User author);

    Optional<Tweet> findByIdAndDeletedFalse(Integer id);

    default Tweet tryToFindById(Integer id) throws NotFoundException {
        Optional<Tweet> optionalTweet = findByIdAndDeletedFalse(id);

        if (optionalTweet.isEmpty())
            throw new NotFoundException("No Tweet found with id " + id);
        else
            return optionalTweet.get();
    }

    Collection<Tweet> findAllByInReplyTo(Tweet tweet);
}
