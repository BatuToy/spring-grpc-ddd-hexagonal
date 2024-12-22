package com.batu.grpc.domain.exception;

import com.batu.domain.exception.DomainException;

public class AuthorDomainException extends DomainException {
    public AuthorDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorDomainException(String message) {
        super(message);
    }
}
