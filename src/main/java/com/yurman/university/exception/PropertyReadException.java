package com.yurman.university.exception;

public class PropertyReadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PropertyReadException(String message) {
        super(message);
    }

    public PropertyReadException() {
    }

}
