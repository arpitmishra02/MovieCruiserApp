package com.stackroute.MovieCruiserApp.exceptions;

public class MovieAlreadyExistException extends Exception{

    private String message;

    public MovieAlreadyExistException() {}

    public MovieAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
