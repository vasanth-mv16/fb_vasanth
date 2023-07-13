package com.facebook.customException;

/**
 * <p>
 * Adds Custom Exception
 * </p>
 */
public class WrongFileNameException extends Exception {
    public WrongFileNameException(String errorMessage) {
        super(errorMessage);
    }
}
