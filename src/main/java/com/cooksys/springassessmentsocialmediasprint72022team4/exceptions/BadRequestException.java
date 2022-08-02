package com.cooksys.springassessmentsocialmediasprint72022team4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = 2460976367507386024L;

    private String message;

}
