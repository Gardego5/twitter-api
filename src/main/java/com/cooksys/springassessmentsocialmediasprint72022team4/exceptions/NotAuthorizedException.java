package com.cooksys.springassessmentsocialmediasprint72022team4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotAuthorizedException extends RuntimeException {

    private static final long serialVersionUID = 4927969010473593201L;

    private String message;

}
