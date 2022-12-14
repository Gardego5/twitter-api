package com.cooksys.springassessmentsocialmediasprint72022team4.controllers;

import com.cooksys.springassessmentsocialmediasprint72022team4.entities.User;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.CredentialsDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.TweetResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserRequestDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.UserResponseDto;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @PatchMapping("/@{username}")
    public UserResponseDto updateProfile(@PathVariable String username, @RequestBody UserRequestDto userRequestDto) {
        return userService.updateProfile(username, userRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/@{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/@{username}/feed")
    public List<TweetResponseDto> getUserFeed(@PathVariable String username) {
        return userService.getUserFeed(username);
    }

    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        return userService.deleteUser(username, credentialsDto);
    }

    @PostMapping("/{username}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void followUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.followUser(username, credentialsDto);
    }

    @PostMapping("/{username}/unfollow")
    public void unfollowUser(@PathVariable String username, @RequestBody CredentialsDto credentialsDto) {
        userService.unfollowUser(username, credentialsDto);
    }

    @GetMapping("/{username}/followers")
    public List<UserResponseDto> getFollowers(@PathVariable String username) {
        return userService.getFollowers(username);
    }

    @GetMapping("/{username}/mentions")
    public List<TweetResponseDto> getUserMentions(@PathVariable String username) {
        return userService.getUserMentions(username);
    }
}
