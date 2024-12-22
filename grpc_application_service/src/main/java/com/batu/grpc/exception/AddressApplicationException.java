package com.batu.grpc.exception;

import com.batu.application.exception.ApplicationException;

public class AddressApplicationException extends ApplicationException {
    public AddressApplicationException(String message) {
        super(message);
    }

    public AddressApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
