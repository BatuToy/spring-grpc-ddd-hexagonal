package com.batu.grpc.domain.exception;

import com.batu.common.domain.exception.DomainException;

public class AuthorDomainException extends DomainException {
    public AuthorDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorDomainException(String message) {
        super(message);
    }
}
