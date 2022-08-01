package com.cooksys.springassessmentsocialmediasprint72022team4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
