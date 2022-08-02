package com.cooksys.springassessmentsocialmediasprint72022team4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.Hashtag;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {

    Optional<Hashtag> findByLabel(String label);

    default Hashtag tryToFindByLabel(String label) throws NotFoundException {
        Optional<Hashtag> optionalTweet = findByLabel(label);

        if (optionalTweet.isEmpty())
            throw new NotFoundException("No hashtag with label " + label);
        else
            return optionalTweet.get();
    }
}
