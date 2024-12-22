package com.batu.grpc.exception;

import com.batu.application.exception.ApplicationException;

public class BookApplicationException extends ApplicationException {
    public BookApplicationException(String message) {
        super(message);
    }

    public BookApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
