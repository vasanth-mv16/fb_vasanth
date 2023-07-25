package com.facebook.customException;

/**
 * <p>
 * Handles custom exception for database access exceptions
 * </p>
 */
public class DataBaseAccessException extends RuntimeException {

    public DataBaseAccessException (final String errorMessage) {
        super(errorMessage);
    }
}
