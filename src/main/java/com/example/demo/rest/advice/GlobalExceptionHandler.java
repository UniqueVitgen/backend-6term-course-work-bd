package com.example.demo.rest.advice;

import com.example.demo.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError noHandlerFoundException(
            NoHandlerFoundException ex) {

        int code = 1000;
        String message = "No handler found for your request.";
        return new ApiError(code, message);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError UsernameNotFound(
            Exception ex) {

        int code = 403;
        String message = "User is not exist";
        return new ApiError(code, message);

    }

}
