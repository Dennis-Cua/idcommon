package com.mireccruit.takehome.accessdata.controller;

import com.mireccruit.takehome.accessdata.exceptions.NoTaskAvailableException;
import com.mireccruit.takehome.accessdata.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoTaskAvailableAdvice {

    @ResponseBody
    @ExceptionHandler(NoTaskAvailableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NoTaskAvailableHandler(NoTaskAvailableException ex)
    {
        return ex.getMessage();
    }
}
