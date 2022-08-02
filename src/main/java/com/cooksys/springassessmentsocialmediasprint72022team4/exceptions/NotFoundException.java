package com.cooksys.springassessmentsocialmediasprint72022team4.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends  RuntimeException{

    private static final long serialVersionUID = -3284897261094818808L;

    private String message;

}