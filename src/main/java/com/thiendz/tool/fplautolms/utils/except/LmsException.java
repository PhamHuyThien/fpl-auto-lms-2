package com.thiendz.tool.fplautolms.utils.except;

public class LmsException extends Exception {
    private final String message;

    public LmsException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
