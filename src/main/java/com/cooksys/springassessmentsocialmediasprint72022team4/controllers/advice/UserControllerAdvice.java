package com.cooksys.springassessmentsocialmediasprint72022team4.controllers.advice;

import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.BadRequestException;
import com.cooksys.springassessmentsocialmediasprint72022team4.exceptions.NotFoundException;
import com.cooksys.springassessmentsocialmediasprint72022team4.model.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice (basePackages = { "com.cooksys.springassessmentsocialmediasprint72022team4" })
@ResponseBody
public class UserControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorDto handleBadRequestException(HttpServletRequest request, BadRequestException badRequestException) {
        return new ErrorDto(badRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorDto handleNotFoundException(HttpServletRequest request, NotFoundException notFoundException) {
        return new ErrorDto(notFoundException.getMessage());
    }
}
