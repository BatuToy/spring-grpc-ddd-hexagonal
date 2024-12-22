package com.batu.grpc.domain.exception;

import com.batu.domain.exception.DomainException;

public class ContactDomainException extends DomainException {
    public ContactDomainException(String message) {
        super(message);
    }

    public ContactDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
