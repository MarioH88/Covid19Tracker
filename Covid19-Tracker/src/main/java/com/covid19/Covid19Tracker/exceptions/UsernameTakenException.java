package com.covid19.Covid19Tracker.exceptions;

public class UsernameTakenException extends Exception {

    public UsernameTakenException(String username, Throwable cause) {
        super(username, cause);
    }
}
