package com.foxminded.university.exception;

public class QueryNotExecuteException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QueryNotExecuteException(String message) {
        super(message);
    }

}
