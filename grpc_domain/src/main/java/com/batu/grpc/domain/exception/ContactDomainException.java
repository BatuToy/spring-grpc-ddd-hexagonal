package com.batu.grpc.domain.exception;

import com.batu.common.domain.exception.DomainException;

public class ContactDomainException extends DomainException {
    public ContactDomainException(String message) {
        super(message);
    }

    public ContactDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
