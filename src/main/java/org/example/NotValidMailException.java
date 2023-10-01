package org.example;

public class NotValidMailException extends Exception {
    public NotValidMailException() {

    }
    public NotValidMailException(String message) {
        super(message);
    }
}