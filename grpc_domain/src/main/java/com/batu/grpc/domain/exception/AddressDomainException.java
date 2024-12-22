package com.batu.grpc.domain.exception;

import com.batu.domain.exception.DomainException;

public class AddressDomainException extends DomainException {
    public AddressDomainException(String message) {
        super(message);
    }

    public AddressDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
