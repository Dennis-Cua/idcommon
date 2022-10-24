package com.mireccruit.takehome.accessdata.controller;

import com.mireccruit.takehome.accessdata.exceptions.NoTaskAvailableException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DataAccessExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String DataAccessExceptionHandler(DataAccessException ex) {
        return ex.getCause().getMessage();
    }
}
