package com.foxminded.university.dao;

public class DaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    DaoException(String message) {
	super(message);
    }

}
