package com.mireccruit.takehome.accessdata.exceptions;

public class NoTaskAvailableException extends RuntimeException{
    public NoTaskAvailableException()
    {
        super("No task available");
    }
}
