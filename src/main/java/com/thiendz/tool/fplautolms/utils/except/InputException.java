package com.thiendz.tool.fplautolms.utils.except;

public class InputException extends Exception {
    private final String message;

    public InputException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
