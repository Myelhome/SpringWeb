package com.example.demoTest.exceptions;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(String message) {
        super(message);
    }
}
