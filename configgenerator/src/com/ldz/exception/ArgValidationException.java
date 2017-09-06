package com.ldz.exception;

/**
 * Created by Loic on 06/09/2017.
 */
public class ArgValidationException extends RuntimeException {
    public ArgValidationException() {
    }

    public ArgValidationException(String message) {
        super(message);
    }

    public ArgValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArgValidationException(Throwable cause) {
        super(cause);
    }

}
