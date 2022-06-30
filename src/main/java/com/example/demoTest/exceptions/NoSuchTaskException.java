package com.example.demoTest.exceptions;

public class NoSuchTaskException extends Exception{
    public NoSuchTaskException(String message) {
        super(message);
    }
}
