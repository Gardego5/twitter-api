package com.cooksys.springassessmentsocialmediasprint72022team4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TweetRequestDto {

    private String content;

    private CredentialsDto credentials;
}
