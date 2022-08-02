package com.cooksys.springassessmentsocialmediasprint72022team4.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByCredentials_Username(String username);

    default public User getOptionalByUsername(String username) {
        Optional<User> optionalTweet = findByCredentials_Username(username);

        if (optionalTweet.isEmpty())
            throw new NotFoundException("No User found with username " + username);
        else
            return optionalTweet.get();
    }
}
