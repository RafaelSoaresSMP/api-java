package com.eventos.eventos.exceptions;

public class UserExistException extends RuntimeException{
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
