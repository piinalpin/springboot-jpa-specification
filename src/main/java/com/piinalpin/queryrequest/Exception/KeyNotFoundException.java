package com.piinalpin.queryrequest.Exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException() {
        super("Invalid Key");
    }
}