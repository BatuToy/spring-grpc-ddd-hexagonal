package com.batu.grpc.exception;

import com.batu.application.exception.ApplicationException;

public class ContactApplicationException extends ApplicationException {
    public ContactApplicationException(String message) {
        super(message);
    }

    public ContactApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
