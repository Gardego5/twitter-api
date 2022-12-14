package com.cooksys.springassessmentsocialmediasprint72022team4.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.springassessmentsocialmediasprint72022team4.services.HashtagService;
import com.cooksys.springassessmentsocialmediasprint72022team4.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private final UserService userService;
    private final HashtagService hashtagService;

    @GetMapping("/username/exists/@{username}")
    public Boolean usernameExists(@PathVariable String username) {
        return userService.usernameExists(username);
    }

    @GetMapping("/username/available/@{username}")
    public Boolean usernameAvailable(@PathVariable String username) {
        return userService.usernameAvailable(username);
    }

    @GetMapping("/tag/exists/{label}")
    public Boolean tagExists(@PathVariable String label) {
        return hashtagService.tagExists(label);
    }
}
