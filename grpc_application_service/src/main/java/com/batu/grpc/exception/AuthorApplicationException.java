package com.batu.grpc.exception;

import com.batu.application.exception.ApplicationException;

public class AuthorApplicationException extends ApplicationException {
    public AuthorApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorApplicationException(String message) {
        super(message);
    }
}
