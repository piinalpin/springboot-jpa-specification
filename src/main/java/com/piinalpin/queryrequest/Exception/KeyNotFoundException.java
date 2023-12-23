package com.piinalpin.queryrequest.Exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String key) {
        super(key + " is invalid Key");
    }
}